package com.cony.projects.fss.goods.controller;

import com.cony.projects.fss.goods.service.IGoodsService;
import com.cony.projects.fss.goods.entity.Goods;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：Goods控制层
*/
@RestController
@RequestMapping("/api/goods")
@Api(value = "GoodsController", description = "Goods API")
public class GoodsController extends AbstractSpringController<Goods,IGoodsService> {

}
