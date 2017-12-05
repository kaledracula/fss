package com.cony.security.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.security.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wangk-p on 2017/11/22.
 */
@Repository
public class UserRepository extends AbstractJpaRepository<User> implements IUserDao {

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (!StringUtils.isEmpty(params.get("username"))) {
                queryBuilder.like("username", "%" + params.get("username") + "%");
            }
            if (!StringUtils.isEmpty(params.get("name"))) {
                queryBuilder.like("name", "%" + params.get("name") + "%");
            }
            if (!StringUtils.isEmpty(params.get("mobilePhone"))) {
                queryBuilder.eq("mobilePhone", params.get("mobilePhone"));
            }
            if (!StringUtils.isEmpty(params.get("loginName"))) {
                List<String> fields = new ArrayList<>();
                fields.add("mobilePhone");
                fields.add("username");
                queryBuilder.or(fields, params.get("loginName"));
            }
        }
    }
}
