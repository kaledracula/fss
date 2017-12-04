package com.cony.projects.fss.basic.service;

import com.cony.projects.fss.basic.dao.ISalesmanDao;
import com.cony.projects.fss.basic.entity.Salesman;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：Salesman 服务实现层
*/
@Service
public class SalesmanServiceImpl extends AbstractService<Salesman,ISalesmanDao> implements ISalesmanService{

    @Override
    protected String doValidate(Salesman entity) {
        return null;
    }
}
