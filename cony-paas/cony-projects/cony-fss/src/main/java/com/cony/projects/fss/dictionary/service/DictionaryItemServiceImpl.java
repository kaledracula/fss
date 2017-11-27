package com.cony.projects.fss.dictionary.service;

import com.cony.projects.fss.dictionary.dao.IDictionaryItemDao;
import com.cony.projects.fss.dictionary.entity.DictionaryItem;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：DictionaryItem 服务实现层
*/
@Service
public class DictionaryItemServiceImpl extends AbstractService<DictionaryItem,IDictionaryItemDao> implements IDictionaryItemService{

    @Override
    protected String doValidate(DictionaryItem entity) {
        return null;
    }
}
