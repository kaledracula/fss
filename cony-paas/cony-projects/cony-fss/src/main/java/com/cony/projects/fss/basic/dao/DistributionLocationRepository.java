package com.cony.projects.fss.basic.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.basic.entity.*;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Map;

/**
* 描述：DistributionLocation 数据层实现
*/
@Repository
public class DistributionLocationRepository  extends AbstractJpaRepository<DistributionLocation> implements IDistributionLocationDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (!StringUtils.isEmpty(params.get("location"))) {
                queryBuilder.like("location", "%" + params.get("location") + "%");
            }
            if (!StringUtils.isEmpty(params.get("status"))) {
                queryBuilder.eq("status", ActiveStatus.valueOf((String) params.get("status")));
            }
            if (!StringUtils.isEmpty(params.get("marketGroupId"))) {
                Join<MarketGroup, DistributionLocation> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("marketGroup", MarketGroup.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("id"), params.get("marketGroupId")));
            }
            if (!StringUtils.isEmpty(params.get("warehouseId"))) {
                Join<Warehouse, DistributionLocation> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("warehouse", Warehouse.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("id"), params.get("warehouseId")));
            }
        }
    }
}
