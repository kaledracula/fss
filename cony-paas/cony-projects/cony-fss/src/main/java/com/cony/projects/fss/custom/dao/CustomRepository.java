package com.cony.projects.fss.custom.dao;

import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.projects.fss.custom.entity.Custom;
import com.cony.projects.fss.dictionary.entity.Dictionary;
import com.cony.projects.fss.dictionary.entity.DictionaryItem;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.util.Date;
import java.util.Map;

/**
 * 描述：Custom 数据层实现
 */
@Repository
public class CustomRepository extends AbstractJpaRepository<Custom> implements ICustomDao {

    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
        if (params != null) {
            if (!StringUtils.isEmpty(params.get("name"))) {
                queryBuilder.like("name","%"+params.get("name")+"%");
            }
            if (!StringUtils.isEmpty(params.get("username"))) {
                queryBuilder.like("username","%"+params.get("username")+"%");
            }
            if (!StringUtils.isEmpty(params.get("linkman"))) {
                queryBuilder.like("linkman","%"+params.get("linkman")+"%");
            }
            if (!StringUtils.isEmpty(params.get("mobilePhone"))) {
                queryBuilder.like("mobilePhone","%"+params.get("mobilePhone")+"%");
            }
            if (!StringUtils.isEmpty(params.get("address"))) {
                queryBuilder.like("address","%"+params.get("address")+"%");
            }
            if (!StringUtils.isEmpty(params.get("type"))) {
                queryBuilder.eq("type",params.get("type"));
            }
            if (!StringUtils.isEmpty(params.get("status"))) {
                queryBuilder.eq("status",params.get("status"));
            }
            if (!StringUtils.isEmpty(params.get("beginTime")) && StringUtils.isEmpty(params.get("endTime"))) {
                queryBuilder.between("createTime",(Date)params.get("beginTime"),(Date)params.get("endTime"));
            }
        }
    }
}
