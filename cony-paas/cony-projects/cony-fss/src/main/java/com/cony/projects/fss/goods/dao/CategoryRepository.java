package com.cony.projects.fss.goods.dao;

import com.cony.data.jpa.repository.AbstractTreeJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.goods.entity.Category;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Map;

/**
 * 描述：Category 数据层实现
 */
@Repository
public class CategoryRepository extends AbstractTreeJpaRepository<Category> implements ICategoryDao {

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (!StringUtils.isEmpty(params.get("parentId"))) {
                queryBuilder.eq("parentId", params.get("parentId"));
            } else {
                queryBuilder.isNull("parentId");
            }
        }
    }
}
