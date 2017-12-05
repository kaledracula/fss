package com.cony.security.service;

import com.cony.data.common.validate.ValidateUtils;
import com.cony.security.entity.User;
import com.cony.security.util.ValidateCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by wangk-p on 2017/11/22.
 */
public class UserLoginService implements UserDetailsService {

    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Map<String,Object> params = new HashMap<>();
        params.put("loginName",s);
        User user;
        try {
            user = userService.queryUnique(params);
        } catch (EmptyResultDataAccessException e) {
            throw new UsernameNotFoundException("用户名或手机号码不存在");
        }
        return user;
    }
}
