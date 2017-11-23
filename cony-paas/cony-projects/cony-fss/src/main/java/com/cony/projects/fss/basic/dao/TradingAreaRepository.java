package com.cony.projects.fss.basic.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.basic.entity.TradingArea;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
* 描述：TradingArea 数据层实现
*/
@Repository
public class TradingAreaRepository  extends AbstractJpaRepository<TradingArea> implements ITradingAreaDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (params.containsKey("name")) {
                if (!StringUtils.isEmpty(params.get("name"))) {
                    queryBuilder.like("name","%"+params.get("name")+"%");
                }
            }
        }
    }
}
