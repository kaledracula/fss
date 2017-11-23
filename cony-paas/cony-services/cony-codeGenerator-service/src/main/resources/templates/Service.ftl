package ${package_name}.service;

import ${package_name}.dao.I${table_name}Dao;
import ${package_name}.entity.${table_name};
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：${table_name} 服务实现层
*/
@Service
public class ${table_name}ServiceImpl extends AbstractService<${table_name},I${table_name}Dao> implements I${table_name}Service{

    @Override
    protected String doValidate(${table_name} entity) {
        return null;
    }
}
