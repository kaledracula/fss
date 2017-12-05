package com.cony.projects.fss.goods.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.custom.entity.Department;
import com.cony.projects.fss.goods.entity.GoodsMenu;
import com.cony.projects.fss.goods.entity.Category;
import com.cony.projects.fss.goods.entity.Goods;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Map;

/**
* 描述：GoodsMenu 数据层实现
*/
@Repository
public class GoodsMenuRepository extends AbstractJpaRepository<GoodsMenu> implements IGoodsMenuDao {

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (!StringUtils.isEmpty(params.get("departmentId"))) {
                queryBuilder.eq("departmentId",params.get("departmentId"));
            }
            if (!StringUtils.isEmpty(params.get("goodsName"))) {
                Join<Goods, GoodsMenu> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("goods", Goods.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().like(join.get("name"), "%"+params.get("goods")+"%"));
            }
            if (!StringUtils.isEmpty(params.get("goodsCode"))) {
                Join<Goods, GoodsMenu> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("goods", Goods.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().like(join.get("code"), "%"+params.get("goodsCode")+"%"));
            }
            if (!StringUtils.isEmpty(params.get("categoryLv1Id"))) {
                Join<Category, GoodsMenu> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("categoryLv1", Category.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("categoryLv1"), params.get("categoryLv1Id")));
            }
            if (!StringUtils.isEmpty(params.get("categoryLv2Id"))) {
                Join<Category, GoodsMenu> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("categoryLv2", Category.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("categoryLv2"), params.get("categoryLv2Id")));
            }
        }
    }
}
