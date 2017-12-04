package com.cony.projects.fss.basic.controller;

import com.cony.projects.fss.basic.entity.Collector;
import com.cony.projects.fss.basic.service.ICollectorService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：Collector控制层
*/
@RestController
@RequestMapping("/api/basic/collector")
@Api(value = "CollectorController", description = "Collector API")
public class CollectorController extends AbstractSpringController<Collector,ICollectorService> {

}
