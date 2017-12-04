package com.cony.projects.fss.basic.controller;

import com.cony.projects.fss.basic.entity.GoodsLocation;
import com.cony.projects.fss.basic.service.IGoodsLocationService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：GoodsLocation控制层
*/
@RestController
@RequestMapping("/api/basic/goodsLocation")
@Api(value = "GoodsLocationController", description = "GoodsLocation API")
public class GoodsLocationController extends AbstractSpringController<GoodsLocation,IGoodsLocationService> {

}
