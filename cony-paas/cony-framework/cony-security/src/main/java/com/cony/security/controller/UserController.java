package com.cony.security.controller;

import com.cony.security.entity.User;
import com.cony.security.service.IUserService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangk-p on 2017/11/22.
 */
@RestController
@RequestMapping("/api/security/user")
@Api(value = "UserController", description = "User API")
public class UserController extends AbstractSpringController<User,IUserService> {

}
