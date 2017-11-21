package com.cony.file.dao;


import com.cony.data.jpa.repository.AbstractJpaRepository;
import com.cony.data.jpa.repository.QueryBuilder;
import com.cony.file.entity.SimpleFile;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by wangk-p on 2017/5/19.
 */
@Service
public class SimpleFileRepository extends AbstractJpaRepository<SimpleFile> implements  ISimpleFileDao{
    @Override
    protected void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder) {
            if(params != null && params.size()>0) {
                if(!isNullOrEmpty(params,"md5")) {
                    queryBuilder.eq("md5",params.get("md5"));
                }
            }
    }
}
