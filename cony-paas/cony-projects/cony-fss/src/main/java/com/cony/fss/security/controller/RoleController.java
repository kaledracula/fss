package com.cony.fss.security.controller;

import com.cony.fss.security.entity.Role;
import com.cony.fss.security.service.IRoleService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wangk-p on 2017/11/22.
 */
@RestController
@RequestMapping("/role")
@Api(value = "RoleController", description = "角色接口")
public class RoleController extends AbstractSpringController<Role,IRoleService> {

}
