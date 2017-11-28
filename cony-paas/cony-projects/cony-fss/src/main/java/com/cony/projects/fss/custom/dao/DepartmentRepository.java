package com.cony.projects.fss.custom.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.custom.entity.Department;
import com.cony.projects.fss.dictionary.entity.Dictionary;
import com.cony.projects.fss.dictionary.entity.DictionaryItem;
import com.cony.projects.fss.goods.entity.Goods;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Date;
import java.util.Map;

/**
* 描述：Department 数据层实现
*/
@Repository
public class DepartmentRepository  extends AbstractJpaRepository<Department> implements IDepartmentDao{

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
    }
}
