package com.cony.projects.fss.custom.service;

import com.cony.projects.fss.custom.dao.ICustomDao;
import com.cony.projects.fss.custom.entity.Custom;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：Custom 服务实现层
*/
@Service
public class CustomServiceImpl extends AbstractService<Custom,ICustomDao> implements ICustomService{

    @Override
    protected String doValidate(Custom entity) {
        return null;
    }
}
