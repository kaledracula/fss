package com.cony.projects.fss.basic.controller;

import com.cony.projects.fss.basic.entity.TradingArea;
import com.cony.projects.fss.basic.service.ITradingAreaService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：TradingArea控制层
*/
@RestController
@RequestMapping("/tradingArea")
@Api(value = "TradingAreaController", description = "TradingArea API")
public class TradingAreaController extends AbstractSpringController<TradingArea,ITradingAreaService> {

}
