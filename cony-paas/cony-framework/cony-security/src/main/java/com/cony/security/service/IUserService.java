package com.cony.security.service;


import com.cony.security.dao.IUserDao;
import com.cony.security.entity.User;
import com.cony.web.service.IService;

/**
 * Created by wangk-p on 2017/11/22.
 */
public interface IUserService extends IService<User,IUserDao> {
}
