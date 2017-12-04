package com.cony.projects.fss.basic.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.basic.entity.Warehouse;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
* 描述：Warehouse 数据层实现
*/
@Repository
public class WareHouseRepository  extends AbstractJpaRepository<Warehouse> implements IWareHouseDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {

    }
}
