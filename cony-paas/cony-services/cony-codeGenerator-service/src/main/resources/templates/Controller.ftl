package ${package_name}.controller;

import ${package_name}.entity.${table_name};
import ${package_name}.service.I${table_name}Service;
import com.cony.web.controller.AbstractSpringController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 描述：${table_name}控制层
*/
@RestController
@RequestMapping("/api/${module}/${table_name?uncap_first}")
@Api(value = "${table_name}Controller", description = "${table_name} API")
public class ${table_name}Controller extends AbstractSpringController<${table_name},I${table_name}Service> {

}
