package com.cony.fssWC.service;

import com.cony.fssWC.dao.IRoleDao;
import com.cony.fssWC.entity.Role;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
 * Created by wangk-p on 2017/11/22.
 */
@Service
public class RoleServiceImpl extends AbstractService<Role,IRoleDao> implements IRoleService{
    @Override
    protected String doValidate(Role entity) {
        return null;
    }
}
