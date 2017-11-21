package com.cony.file.service;



import com.cony.file.dao.ISimpleFileDao;
import com.cony.file.entity.SimpleFile;
import com.cony.web.service.IService;

import java.util.List;

/**
 * Created by wangk-p on 2017/5/19.
 */
public interface ISimpleFileService extends IService<SimpleFile,ISimpleFileDao> {


    List<SimpleFile> getFileByMD5(String md5);
}
