package com.cony.projects.fss.basic.service;

import com.cony.projects.fss.basic.dao.IWareHouseDao;
import com.cony.projects.fss.basic.entity.Warehouse;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：Warehouse 服务实现层
*/
@Service
public class WareHouseServiceImpl extends AbstractService<Warehouse,IWareHouseDao> implements IWareHouseService{

    @Override
    protected String doValidate(Warehouse entity) {
        return null;
    }
}
