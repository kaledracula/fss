package com.cony.projects.fss.dictionary.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.dictionary.entity.Dictionary;
import com.cony.projects.fss.dictionary.entity.DictionaryItem;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Map;

/**
* 描述：DictionaryItem 数据层实现
*/
@Repository
public class DictionaryItemRepository  extends AbstractJpaRepository<DictionaryItem> implements IDictionaryItemDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (StringUtils.isEmpty(params.get("dictCode"))) {
                Join<Dictionary, DictionaryItem> join = queryBuilder.getform().join(queryBuilder.getform().getModel().getSingularAttribute("dictionaryId", Dictionary.class), JoinType.LEFT);
                queryBuilder.addCriterions(queryBuilder.getCriteriaBuilder().equal(join.get("code"), params.get("dictCode")));
            }
        }
    }
}
