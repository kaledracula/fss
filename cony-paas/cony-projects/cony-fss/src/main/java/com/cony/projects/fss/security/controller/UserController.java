package com.cony.projects.fss.security.controller;

import com.cony.projects.fss.security.entity.User;
import com.cony.projects.fss.security.service.IUserService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangk-p on 2017/11/22.
 */
@RestController
@RequestMapping("/user")
@Api(value = "UserController", description = "用户接口")
public class UserController extends AbstractSpringController<User,IUserService> {

}
