package com.cony.projects.fss.dictionary.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.dictionary.entity.Dictionary;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* 描述：Dictionary 数据层实现
*/
@Repository
public class DictionaryRepository  extends AbstractJpaRepository<Dictionary> implements IDictionaryDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {

    }
}
