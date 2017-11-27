package com.cony.projects.fss.dictionary.service;

import com.cony.projects.fss.dictionary.dao.IDictionaryDao;
import com.cony.projects.fss.dictionary.entity.Dictionary;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：Dictionary 服务实现层
*/
@Service
public class DictionaryServiceImpl extends AbstractService<Dictionary,IDictionaryDao> implements IDictionaryService{

    @Override
    protected String doValidate(Dictionary entity) {
        return null;
    }
}
