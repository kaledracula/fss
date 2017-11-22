package com.cony.fss.security.service;

import com.cony.fss.security.dao.IRoleDao;
import com.cony.fss.security.entity.Role;
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
