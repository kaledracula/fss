package com.cony.projects.fss.custom.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.custom.entity.Custom;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* 描述：Custom 数据层实现
*/
@Repository
public class CustomRepository  extends AbstractJpaRepository<Custom> implements ICustomDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {

    }
}
