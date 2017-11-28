package com.cony.projects.fss.custom.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.custom.entity.Department;
import com.cony.projects.fss.custom.entity.Menu;
import com.cony.projects.fss.dictionary.entity.Dictionary;
import com.cony.projects.fss.goods.entity.Category;
import com.cony.projects.fss.goods.entity.Goods;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
* 描述：Menu 数据层实现
*/
@Repository
public class MenuRepository  extends AbstractJpaRepository<Menu> implements IMenuDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (!StringUtils.isEmpty(params.get("departmentId"))) {
                Join<Department, Menu> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("department", Department.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("id"), params.get("departmentId")));
            }
            if (!StringUtils.isEmpty(params.get("goodsName"))) {
                Join<Goods, Menu> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("goods", Goods.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().like(join.get("name"), "%"+params.get("goods")+"%"));
            }
            if (!StringUtils.isEmpty(params.get("goodsCode"))) {
                Join<Goods, Menu> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("goods", Goods.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().like(join.get("code"), "%"+params.get("goodsCode")+"%"));
            }
            if (!StringUtils.isEmpty(params.get("categoryLv1Id"))) {
                Join<Category, Goods> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("goods", Goods.class), JoinType.LEFT).join(queryBuilder.getform().getModel().getSingularAttribute("categoryLv1", Category.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("categoryLv1Id"), params.get("categoryLv1Id")));
            }
            if (!StringUtils.isEmpty(params.get("categoryLv2Id"))) {
                Join<Category, Goods> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("goods", Goods.class), JoinType.LEFT).join(queryBuilder.getform().getModel().getSingularAttribute("categoryLv1", Category.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("categoryLv2Id"), params.get("categoryLv2Id")));
            }
        }
    }
}
