package com.cony.projects.fss.custom.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.custom.entity.AccountPeriod;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* 描述：AccountPeriod 数据层实现
*/
@Repository
public class AccountPeriodRepository  extends AbstractJpaRepository<AccountPeriod> implements IAccountPeriodDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {

    }
}
