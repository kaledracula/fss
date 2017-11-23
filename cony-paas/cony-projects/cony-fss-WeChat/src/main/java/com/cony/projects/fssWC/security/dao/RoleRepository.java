package com.cony.projects.fssWC.security.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fssWC.security.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* 描述：Role 数据层实现
*/
@Repository
public class RoleRepository  extends AbstractJpaRepository<Role> implements IRoleDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {

    }
}
