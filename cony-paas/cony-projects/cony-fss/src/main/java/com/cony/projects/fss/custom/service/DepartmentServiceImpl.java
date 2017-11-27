package com.cony.projects.fss.custom.service;

import com.cony.projects.fss.custom.dao.IDepartmentDao;
import com.cony.projects.fss.custom.entity.Department;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：Department 服务实现层
*/
@Service
public class DepartmentServiceImpl extends AbstractService<Department,IDepartmentDao> implements IDepartmentService{

    @Override
    protected String doValidate(Department entity) {
        return null;
    }
}
