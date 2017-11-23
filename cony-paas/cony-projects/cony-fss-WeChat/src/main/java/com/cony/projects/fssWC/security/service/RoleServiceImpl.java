package com.cony.projects.fssWC.security.service;


import com.cony.projects.fssWC.security.dao.IRoleDao;
import com.cony.projects.fssWC.security.entity.Role;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：Role 服务实现层
*/
@Service
public class RoleServiceImpl extends AbstractService<Role,IRoleDao> implements IRoleService{

    @Override
    protected String doValidate(Role entity) {
        return null;
    }
}
