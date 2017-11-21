package com.cony.file.web.controller;

import com.cony.excel.utils.ExcelUtil;
import com.cony.file.entity.SimpleFile;
import com.cony.file.service.ISimpleFileService;
import com.cony.file.web.config.AbstractFileTransfer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Paths;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/download")
public class DownloadController extends AbstractFileTransfer {

	@Autowired
	private ISimpleFileService iSimpleFileService;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private MessageSource messageSource;

	/**
	 * 日志，子类可以直接使用。
	 */
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());


	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public void download(@PathVariable Long id, HttpServletRequest request,
                         HttpServletResponse response) throws Exception {
		doDownload(request, response, id);
	}

	@RequestMapping(value = "/exportExcel/{fileName}", method = RequestMethod.POST)
	public void exportExcel(@PathVariable String fileName, @RequestBody List<Map> list, HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
		LinkedHashMap<String, Object> linkedHashMap = (LinkedHashMap<String, Object>) list.remove(0);
		String[] titles = new String[linkedHashMap.size()];
		int i = 0;
		for (Map.Entry<String, Object> entry : linkedHashMap.entrySet()) {
			titles[i++] = (String) entry.getValue();
		}
		// 设置下载文件的类型、字符集等。
		response.addHeader("Content-Disposition", "attachment;filename=\""
				+ java.net.URLEncoder.encode(fileName, "UTF-8") + "\"");
		ExcelUtil.exportExcel(titles, list, response.getOutputStream());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/getFile/{md5}")
	@ResponseBody
	public ResponseEntity<?> getFile(@PathVariable String md5) {
		try {
			return ResponseEntity.ok(resourceLoader.getResource("file:" + Paths.get(getStorageRootPath()+File.separator, md5).toString()));
		} catch (Exception e) {
			return ResponseEntity.notFound().build();
		}
	}

	protected void doDownload(HttpServletRequest request,
                              HttpServletResponse response, Long id) throws Exception {
		SimpleFile simpleFile = iSimpleFileService.get(id);
		// 文件在服务器端的存储路径
		String fileName = simpleFile.getFileName();
		String filePath = new StringBuilder().
				append(File.separator).
				append(simpleFile.getMd5()).toString();

		if (StringUtils.isEmpty(filePath)) {
			throw new Exception("路径错误，系统找不到指定的文件 : " + filePath);
		}
		File file = new File(getStorageRootPath() + File.separator + filePath);
		if (!file.exists()) {
			throw new Exception("路径错误，系统找不到指定的文件 : " + filePath);
		}

		// 设置下载文件的类型、字符集等。
		response.addHeader("Content-Disposition", "attachment;filename=\""
				+ java.net.URLEncoder.encode(fileName, "UTF-8") + "\"");
		byte[] buffer = new byte[4096];
		BufferedOutputStream output = null;
		BufferedInputStream input = null;
		// 写缓冲区：
		try {
			output = new BufferedOutputStream(response.getOutputStream());
			input = new BufferedInputStream(new FileInputStream(file));
			int n = -1;
			while ((n = input.read(buffer, 0, 4096)) != -1) {
				output.write(buffer, 0, n);
			}
			response.flushBuffer();
		} catch (Exception e) {
			logger.error(e.getMessage());
		} finally {
			try {
				if (input != null)
					input.close();
				if (output != null)
					output.close();
			} catch (IOException e) {
				logger.error(e.getMessage());
			}
		}

	}

}
