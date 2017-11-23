package com.cony.projects.fss.security.service;

import com.cony.projects.fss.security.dao.IUserDao;
import com.cony.projects.fss.security.entity.User;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
 * Created by wangk-p on 2017/11/22.
 */
@Service
public class UserServiceImpl  extends AbstractService<User,IUserDao> implements IUserService {

    @Override
    protected String doValidate(User entity) {
        return null;
    }
}
