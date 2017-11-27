package com.cony.projects.fss.custom.controller;

import com.cony.projects.fss.custom.entity.Menu;
import com.cony.projects.fss.custom.service.IMenuService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：Menu控制层
*/
@RestController
@RequestMapping("/menu")
@Api(value = "MenuController", description = "Menu API")
public class MenuController extends AbstractSpringController<Menu,IMenuService> {

}
