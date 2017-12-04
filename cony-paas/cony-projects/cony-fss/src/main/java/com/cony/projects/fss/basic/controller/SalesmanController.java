package com.cony.projects.fss.basic.controller;

import com.cony.projects.fss.basic.service.ISalesmanService;
import com.cony.projects.fss.basic.entity.Salesman;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：Salesman控制层
*/
@RestController
@RequestMapping("/api/basic/salesman")
@Api(value = "SalesmanController", description = "Salesman API")
public class SalesmanController extends AbstractSpringController<Salesman,ISalesmanService> {

}
