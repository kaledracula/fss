package com.cony.projects.fss.basic.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.basic.entity.Driver;
import com.cony.projects.fss.basic.entity.MarketGroup;
import com.cony.projects.fss.basic.entity.Salesman;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Map;

/**
* 描述：Driver 数据层实现
*/
@Repository
public class DriverRepository  extends AbstractJpaRepository<Driver> implements IDriverDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (params.containsKey("name")) {
                if (!StringUtils.isEmpty(params.get("name"))) {
                    queryBuilder.like("name","%"+params.get("name")+"%");
                }
            }
            if (params.containsKey("mobilePhone")) {
                if (!StringUtils.isEmpty(params.get("mobilePhone"))) {
                    queryBuilder.like("mobilePhone","%"+params.get("mobilePhone")+"%");
                }
            }
            if (params.containsKey("carNumber")) {
                if (!StringUtils.isEmpty(params.get("carNumber"))) {
                    queryBuilder.like("carNumber","%"+params.get("carNumber")+"%");
                }
            }
            if (params.containsKey("carType")) {
                if (!StringUtils.isEmpty(params.get("carType"))) {
                    queryBuilder.like("carType","%"+params.get("carType")+"%");
                }
            }
            if (!StringUtils.isEmpty(params.get("marketGroupId"))) {
                Join<MarketGroup, Driver> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("marketGroup", MarketGroup.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("id"), params.get("marketGroupId")));
            }
        }
    }
}
