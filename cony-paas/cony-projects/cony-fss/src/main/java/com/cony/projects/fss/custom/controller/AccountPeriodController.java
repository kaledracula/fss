package com.cony.projects.fss.custom.controller;

import com.cony.projects.fss.custom.service.IAccountPeriodService;
import com.cony.projects.fss.custom.entity.AccountPeriod;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：AccountPeriod控制层
*/
@RestController
@RequestMapping("/api/custom/accountPeriod")
@Api(value = "AccountPeriodController", description = "AccountPeriod API")
public class AccountPeriodController extends AbstractSpringController<AccountPeriod,IAccountPeriodService> {

}
