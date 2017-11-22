package com.cony.fss.security.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.fss.security.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * Created by wangk-p on 2017/11/22.
 */
@Repository
public class UserRepository extends AbstractJpaRepository<User> implements IUserDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {

    }
}