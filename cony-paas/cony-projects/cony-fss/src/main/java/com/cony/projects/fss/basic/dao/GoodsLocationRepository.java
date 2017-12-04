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
* 描述：GoodsLocation 数据层实现
*/
@Repository
public class GoodsLocationRepository  extends AbstractJpaRepository<GoodsLocation> implements IGoodsLocationDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (!StringUtils.isEmpty(params.get("location"))) {
                queryBuilder.like("location", "%" + params.get("location") + "%");
            }
            if (!StringUtils.isEmpty(params.get("status"))) {
                queryBuilder.eq("status", ActiveStatus.valueOf((String) params.get("status")));
            }
            if (!StringUtils.isEmpty(params.get("type"))) {
                queryBuilder.eq("type", GoodsLocationType.valueOf((String) params.get("type")));
            }
            if (!StringUtils.isEmpty(params.get("warehouseId"))) {
                Join<Warehouse, GoodsLocation> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("warehouse", Warehouse.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("id"), params.get("warehouseId")));
            }
            if (!StringUtils.isEmpty(params.get("collectorId"))) {
                Join<Collector, GoodsLocation> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("collector", Collector.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("id"), params.get("collectorId")));
            }
        }
    }
}
