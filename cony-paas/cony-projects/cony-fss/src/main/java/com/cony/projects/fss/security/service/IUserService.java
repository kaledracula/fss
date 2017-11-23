package com.cony.projects.fss.security.service;

import com.cony.projects.fss.security.dao.IUserDao;
import com.cony.projects.fss.security.entity.User;
import com.cony.web.service.IService;

/**
 * Created by wangk-p on 2017/11/22.
 */
public interface IUserService extends IService<User,IUserDao> {
}
