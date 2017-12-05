package com.cony.projects.fss.goods.controller;

import com.cony.projects.fss.goods.entity.GoodsMenu;
import com.cony.projects.fss.goods.service.IGoodsMenuService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：Menu控制层
*/
@RestController
@RequestMapping("/api/goods/menu")
@Api(value = "MenuController", description = "GoodsMenu API")
public class GoodsMenuController extends AbstractSpringController<GoodsMenu,IGoodsMenuService> {

}
