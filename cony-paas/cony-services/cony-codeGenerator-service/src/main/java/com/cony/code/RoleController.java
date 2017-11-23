package com.cony.projects.fss.security.controller;

import com.cony.projects.fss.security.entity.Role;
import com.cony.projects.fss.security.service.IRoleService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：Role控制层
*/
@RestController
@RequestMapping("/role")
@Api(value = "RoleController")
public class RoleController extends AbstractSpringController<Role,IRoleService> {

}
