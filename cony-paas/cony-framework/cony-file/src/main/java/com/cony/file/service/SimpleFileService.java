package com.cony.file.service;

import com.cony.file.dao.ISimpleFileDao;
import com.cony.file.entity.SimpleFile;

import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wangk-p on 2017/5/19.
 */
@Service
public class SimpleFileService extends AbstractService<SimpleFile,ISimpleFileDao> implements ISimpleFileService {

    @Override
    protected void beforeAdd(SimpleFile entity) {

    }

    @Override
    protected void beforeAdd(Collection<SimpleFile> entities) {

    }

    @Override
    protected void beforeUpdate(SimpleFile entities) {

    }

    @Override
    protected void beforeUpdate(Collection<SimpleFile> entities) {

    }

    @Override
    protected String doValidate(SimpleFile entity) {
        return null;
    }

    @Override
    public List<SimpleFile> getFileByMD5(String md5) {
       Map<String,String> map = new HashMap<String,String>();
       map.put("md5",md5);
        return getDao().query(map);
    }
}
