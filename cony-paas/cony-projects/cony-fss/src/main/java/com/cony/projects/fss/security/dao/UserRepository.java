package com.cony.projects.fss.security.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.security.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * Created by wangk-p on 2017/11/22.
 */
@Repository
public class UserRepository extends AbstractJpaRepository<User> implements IUserDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (params.containsKey("username")) {
                if (StringUtils.isEmpty(params.get("username"))) {
                    queryBuilder.eq("username",params.get("username"));
                }
            }
        }
    }
}
