package com.cony.projects.fss.basic.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.basic.entity.ActiveStatus;
import com.cony.projects.fss.basic.entity.MarketGroup;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
* 描述：MarketGroup 数据层实现
*/
@Repository
public class MarketGroupRepository  extends AbstractJpaRepository<MarketGroup> implements IMarketGroupDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (!StringUtils.isEmpty(params.get("name"))) {
            queryBuilder.like("name", "%" + params.get("name") + "%");
        }
        if (!StringUtils.isEmpty(params.get("status"))) {
            queryBuilder.eq("status", ActiveStatus.valueOf((String) params.get("status")));
        }
    }
}
