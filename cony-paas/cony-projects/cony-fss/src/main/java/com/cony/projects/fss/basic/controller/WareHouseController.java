package com.cony.projects.fss.basic.controller;

import com.cony.projects.fss.basic.service.IWareHouseService;
import com.cony.projects.fss.basic.entity.Warehouse;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：WareHouse控制层
*/
@RestController
@RequestMapping("/api/basic/wareHouse")
@Api(value = "WareHouseController", description = "Warehouse API")
public class WareHouseController extends AbstractSpringController<Warehouse,IWareHouseService> {

}
