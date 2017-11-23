package ${package_name}.service;

import ${package_name}.dao.I${table_name}Dao;
import ${package_name}.entity.${table_name};
import com.cony.web.service.IService;

/**
* 描述：${table_name} 服务实现层接口
*/
public interface I${table_name}Service extends IService<${table_name},I${table_name}Dao> {

}
