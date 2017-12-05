package com.cony.projects.fss.goods.controller;

import com.cony.projects.fss.goods.service.ICategoryService;
import com.cony.projects.fss.goods.entity.Category;
import com.cony.web.controller.AbstractTreeSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：Category控制层
*/
@RestController
@RequestMapping("/api/goods/category")
@Api(value = "CategoryController", description = "Category API")
public class CategoryController extends AbstractTreeSpringController<Category,ICategoryService> {

}
