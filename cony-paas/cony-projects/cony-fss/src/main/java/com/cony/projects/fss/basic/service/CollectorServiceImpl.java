package com.cony.projects.fss.basic.service;

import com.cony.projects.fss.basic.dao.ICollectorDao;
import com.cony.projects.fss.basic.entity.Collector;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：Collector 服务实现层
*/
@Service
public class CollectorServiceImpl extends AbstractService<Collector,ICollectorDao> implements ICollectorService{

    @Override
    protected String doValidate(Collector entity) {
        return null;
    }
}
