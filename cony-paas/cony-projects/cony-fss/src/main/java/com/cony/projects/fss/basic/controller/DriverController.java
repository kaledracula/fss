package com.cony.projects.fss.basic.controller;

import com.cony.projects.fss.basic.entity.Driver;
import com.cony.projects.fss.basic.service.IDriverService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：Driver控制层
*/
@RestController
@RequestMapping("/api/basic/driver")
@Api(value = "DriverController", description = "Driver API")
public class DriverController extends AbstractSpringController<Driver,IDriverService> {

}
