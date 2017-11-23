package ${package_name}.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import ${package_name}.entity.${table_name};
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* 描述：${table_name} 数据层实现
*/
@Repository
public class ${table_name}Repository  extends AbstractJpaRepository<${table_name}> implements I${table_name}Dao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {

    }
}
