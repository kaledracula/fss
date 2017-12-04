package com.cony.security.controller;


import com.cony.security.entity.Role;
import com.cony.security.service.IRoleService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangk-p on 2017/11/22.
 */
@RestController
@RequestMapping("/api/security/role")
@Api(value = "RoleController", description = "Role API")
public class RoleController extends AbstractSpringController<Role,IRoleService> {

}
