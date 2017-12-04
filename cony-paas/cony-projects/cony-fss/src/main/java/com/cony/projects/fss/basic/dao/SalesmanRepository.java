package com.cony.projects.fss.basic.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.basic.entity.MarketGroup;
import com.cony.projects.fss.basic.entity.Salesman;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Map;

/**
 * 描述：Salesman 数据层实现
 */
@Repository
public class SalesmanRepository extends AbstractJpaRepository<Salesman> implements ISalesmanDao {

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (!StringUtils.isEmpty(params.get("name"))) {
                queryBuilder.like("name", "%" + params.get("name") + "%");
            }
            if (!StringUtils.isEmpty(params.get("mobilePhone"))) {
                queryBuilder.like("mobilePhone", "%" + params.get("mobilePhone") + "%");
            }
            if (!StringUtils.isEmpty(params.get("marketGroupId"))) {
                Join<MarketGroup, Salesman> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("marketGroup", MarketGroup.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("id"), params.get("marketGroupId")));
            }
        }
    }
}
