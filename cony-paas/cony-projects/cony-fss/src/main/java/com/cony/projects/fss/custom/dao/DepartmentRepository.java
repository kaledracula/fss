package com.cony.projects.fss.custom.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.custom.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* 描述：Department 数据层实现
*/
@Repository
public class DepartmentRepository  extends AbstractJpaRepository<Department> implements IDepartmentDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {

    }
}
