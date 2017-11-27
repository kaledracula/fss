package com.cony.projects.fss.custom.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.custom.entity.Menu;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* 描述：Menu 数据层实现
*/
@Repository
public class MenuRepository  extends AbstractJpaRepository<Menu> implements IMenuDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {

    }
}
