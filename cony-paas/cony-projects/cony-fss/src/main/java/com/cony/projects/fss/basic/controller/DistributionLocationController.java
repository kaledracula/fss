package com.cony.projects.fss.basic.controller;

import com.cony.projects.fss.basic.entity.DistributionLocation;
import com.cony.projects.fss.basic.service.IDistributionLocationService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：DistributionLocation控制层
*/
@RestController
@RequestMapping("/api/basic/distributionLocation")
@Api(value = "DistributionLocationController", description = "DistributionLocation API")
public class DistributionLocationController extends AbstractSpringController<DistributionLocation,IDistributionLocationService> {

}
