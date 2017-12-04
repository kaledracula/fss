package com.cony.projects.fss.basic.service;

import com.cony.projects.fss.basic.dao.IMarketGroupDao;
import com.cony.projects.fss.basic.entity.MarketGroup;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：MarketGroup 服务实现层
*/
@Service
public class MarketGroupServiceImpl extends AbstractService<MarketGroup,IMarketGroupDao> implements IMarketGroupService{

    @Override
    protected String doValidate(MarketGroup entity) {
        return null;
    }
}
