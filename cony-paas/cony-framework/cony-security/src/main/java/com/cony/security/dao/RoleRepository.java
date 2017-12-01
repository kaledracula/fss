package com.cony.security.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.security.entity.Role;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 描述：Role 数据层实现
 */
@Repository
public class RoleRepository extends AbstractJpaRepository<Role> implements IRoleDao {

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (!StringUtils.isEmpty(params.get("name"))) {
                queryBuilder.like("name", "%" + params.get("name") + "%");
            }
        }
    }
}
