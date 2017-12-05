package com.cony.projects.fss.custom.controller;

import com.cony.projects.fss.custom.entity.Custom;
import com.cony.projects.fss.custom.service.ICustomService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：Custom控制层
*/
@RestController
@RequestMapping("/api/custom")
@Api(value = "CustomController", description = "Custom API")
public class CustomController extends AbstractSpringController<Custom,ICustomService> {

}
