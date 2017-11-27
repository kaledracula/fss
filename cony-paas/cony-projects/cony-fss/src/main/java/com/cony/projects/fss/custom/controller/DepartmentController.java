package com.cony.projects.fss.custom.controller;

import com.cony.projects.fss.custom.entity.Department;
import com.cony.projects.fss.custom.service.IDepartmentService;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：Department控制层
*/
@RestController
@RequestMapping("/department")
@Api(value = "DepartmentController", description = "Department API")
public class DepartmentController extends AbstractSpringController<Department,IDepartmentService> {

}
