package com.cony.projects.fss.basic.service;

import com.cony.projects.fss.basic.dao.ITradingAreaDao;
import com.cony.projects.fss.basic.entity.TradingArea;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：TradingArea 服务实现层
*/
@Service
public class TradingAreaServiceImpl extends AbstractService<TradingArea,ITradingAreaDao> implements ITradingAreaService{

    @Override
    protected String doValidate(TradingArea entity) {
        return null;
    }
}
