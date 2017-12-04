package com.cony.projects.fss.basic.controller;

import com.cony.projects.fss.basic.entity.MarketGroup;
import com.cony.projects.fss.basic.service.IMarketGroupService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：MarketGroup控制层
*/
@RestController
@RequestMapping("/api/basic/marketGroup")
@Api(value = "MarketGroupController", description = "MarketGroup API")
public class MarketGroupController extends AbstractSpringController<MarketGroup,IMarketGroupService> {

}
