package com.cony.file.web.controller;


import com.cony.excel.utils.ExcelLogs;
import com.cony.excel.utils.ExcelUtil;
import com.cony.file.entity.SimpleFile;
import com.cony.file.service.ISimpleFileService;
import com.cony.file.web.config.AbstractFileTransfer;
import com.cony.web.common.result.DefaultResult;
import com.cony.web.common.result.Result;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.*;

@RestController
@RequestMapping("/upload")
public class UploadController extends AbstractFileTransfer {

	/**
	 * 日志，子类可以直接使用。
	 */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ISimpleFileService simpleFileService;

	@Autowired
	private ResourceLoader resourceLoader;

	@RequestMapping(value = "/excelData/{id}", method = RequestMethod.GET)
	public Collection<Map> getExcelData(@PathVariable Long id) throws Exception {
		SimpleFile simpleFile = simpleFileService.get(id);
		// 文件在服务器端的存储路径
		String fileName = simpleFile.getFileName();
		String filePath = new StringBuilder().
				append(simpleFile.getModule()).
				append(File.separator).
				append(simpleFile.getMd5()).
				append(getFilePostfixWithDot(fileName)).toString();

		if (StringUtils.isEmpty(filePath)) {
			throw new Exception("路径错误，系统找不到指定的文件 : " + filePath);
		}
		File file = new File(getStorageRootPath() + File.separator + filePath);
		if (!file.exists()) {
			throw new Exception("路径错误，系统找不到指定的文件 : " + filePath);
		}
		InputStream inputStream = new FileInputStream(file);
		ExcelLogs logs = new ExcelLogs();
		return ExcelUtil.importExcel(Map.class, inputStream, "yyyy/MM/dd HH:mm:ss", logs, 0);
	}

	@RequestMapping(value = "/{module}/{id}/{md5}", method = RequestMethod.POST)
	public Result<Long> upload(@PathVariable String module, @PathVariable String id, @PathVariable String md5, HttpServletRequest request, HttpServletResponse response) throws Exception {

		return new DefaultResult<Long>().setData(uploadFile(module, id, md5, request, response));
	}

	public Long uploadFile(String module, String entityId, String md5, HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		//判断是否是多数据段提交格式

		boolean isMultipart = ServletFileUpload.isMultipartContent(req);
		if (isMultipart) {

			StandardMultipartHttpServletRequest request = (StandardMultipartHttpServletRequest) req;
			Iterator<String> iterator = request.getFileNames();
			File file = null;
			try {
				while (iterator.hasNext()) {
					MultipartFile multipartFile = request.getFile(iterator.next());
					String fileName = multipartFile.getOriginalFilename();

					long size = multipartFile.getSize();
					if ((fileName == null || fileName.equals("")) && size == 0) {
						continue;
					}
					if (!md5Exist(md5)) {
						String filePath = buildFilePath(md5);
						file = new File(filePath);
						multipartFile.transferTo(file);
					}
					return insertFileToDB(fileName, md5, module, entityId, size);
				}
			} catch (Exception e) {
				e.printStackTrace();
				if (file != null) {
					if (file.exists()) {
						file.delete();
					}
				}
				throw new Exception("文件上传失败！");

			}
		}
		return null;
	}

	/**
	 * 创建文件路径
	 *
	 * @param md5
	 * @return
	 * @throws Exception
	 */
	private String buildFilePath(String md5) throws Exception {
		String filePath = new StringBuilder().
				append(getStorageRootPath()).
				append(File.separator).
				append(md5).toString();
		String folderPath = new StringBuilder().
				append(getStorageRootPath()).toString();
		File folder = new File(folderPath);
		if (!folder.exists()) {
			folder.mkdirs();
		}
		return filePath;
	}


	/**
	 * 获取上传文件的在服务器端的名字，多个文件名之间用';'分隔
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	private String getFilesName(HttpServletRequest request,
								HttpServletResponse response) {
		HttpSession session = request.getSession();
		String sessionId = session.getId();
		String uploadFilesNameKey = "FileUpload.FilesName." + sessionId;
		String result = (String) session.getAttribute(uploadFilesNameKey);
		session.setAttribute(uploadFilesNameKey, "");
		return result;
	}

	private Long insertFileToDB(String fileName, String md5, String module, String entityId, Long size) {
		return simpleFileService.add(
				new SimpleFile(
						fileName,
						md5,
						module,
						entityId,
						getFilePostfixWithDot(fileName),
						size
				)
		).getId();
	}

	private boolean md5Exist(String md5) {
		List<SimpleFile> list = simpleFileService.getFileByMD5(md5);
		return list != null && list.size() > 0;
	}

	private String generateMd5(MultipartFile multipartFile) {
		MessageDigest digest = null;
		InputStream in = null;
		byte buffer[] = new byte[1024];
		int len;
		try {
			digest = MessageDigest.getInstance("MD5");
			in = multipartFile.getInputStream();
			while ((len = in.read(buffer, 0, 1024)) != -1) {
				digest.update(buffer, 0, len);
			}
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		BigInteger bigInt = new BigInteger(1, digest.digest());
		return bigInt.toString(16);
	}

}
