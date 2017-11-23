package com.cony.projects.fss.basic.service;

import com.cony.projects.fss.basic.dao.IDriverDao;
import com.cony.projects.fss.basic.entity.Driver;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：Driver 服务实现层
*/
@Service
public class DriverServiceImpl extends AbstractService<Driver,IDriverDao> implements IDriverService{

    @Override
    protected String doValidate(Driver entity) {
        return null;
    }
}
