package com.cony.file.web.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.io.File;


public abstract class AbstractFileTransfer {

	@Autowired
	private FileTransferSetting fileTransferSetting;

	protected FileTransferSetting getFileTransferSetting() {
		return fileTransferSetting;
	}
	/**
	 * 获取上传的文件存储路径的根目录<br/>
	 * 通过配置文件中"storage.root"参数进行设置。
	 *
	 * @return
	 * @throws Exception
	 */
	public String getStorageRootPath() throws Exception {
		String root = fileTransferSetting.getRoot();
		if (StringUtils.isEmpty(root))
			throw new Exception("未指定存储根目录！");
		return root;
	}

	/**
	 * 获取各应用上传文件目录，路径"/root/storage"
	 *
	 * @return
	 * @throws Exception
	 */
	public String getStoragePath(String storage) throws Exception {
		StringBuilder path = new StringBuilder().append(getStorageRootPath())
				.append(File.separator).append(storage);
		File target = new File(path.toString());
		if (!target.exists())
			target.mkdirs();
		return path.toString();
	}
	/**
	 * 获取文件拓展名,包含'.'
	 *
	 * @param fileName
	 * @return
	 */
	public String getFilePostfixWithDot(String fileName) {
		int indexOfDot = fileName.lastIndexOf(".");
		if (indexOfDot + 1 >= fileName.length()) {
			return "";
		} else {
			return fileName.substring(indexOfDot);
		}
	}

	/**
	 * 拆分文件的路径结构
	 *
	 * @param path
	 * @return
	 */
	protected String[] splitPath(String path) {
		String[] paths;
		if(path.contains("/")) {
			paths = path.split("/");
		} else {
			paths = path.split("\\\\");
		}
		return paths;
	}
}
