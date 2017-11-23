package com.cony.fssWC.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.fssWC.entity.Role;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wangk-p on 2017/11/22.
 */
@Repository
public class RoleRepository  extends AbstractJpaRepository<Role> implements IRoleDao{
    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {

    }
}
