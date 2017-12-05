package com.cony.projects.fss.goods.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.custom.entity.Department;
import com.cony.projects.fss.goods.entity.Category;
import com.cony.projects.fss.goods.entity.Goods;
import com.cony.projects.fss.goods.entity.GoodsMenu;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Map;

/**
* 描述：Goods 数据层实现
*/
@Repository
public class GoodsRepository  extends AbstractJpaRepository<Goods> implements IGoodsDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (!StringUtils.isEmpty(params.get("name"))) {
                queryBuilder.like("name", "%"+params.get("name")+"%");            }
            if (!StringUtils.isEmpty(params.get("code"))) {
                queryBuilder.like("code", "%"+params.get("code")+"%");                      }
            if (!StringUtils.isEmpty(params.get("categoryLv1Id"))) {
                Join<Category, Goods> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("categoryLv1", Category.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("categoryLv1"), params.get("categoryLv1Id")));
            }
            if (!StringUtils.isEmpty(params.get("categoryLv2Id"))) {
                Join<Category, Goods> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("categoryLv2", Category.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("categoryLv2"), params.get("categoryLv2Id")));
            }
        }
    }
}
