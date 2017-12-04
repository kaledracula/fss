package com.cony.projects.fss.basic.service;

import com.cony.projects.fss.basic.dao.IGoodsLocationDao;
import com.cony.projects.fss.basic.entity.GoodsLocation;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：GoodsLocation 服务实现层
*/
@Service
public class GoodsLocationServiceImpl extends AbstractService<GoodsLocation,IGoodsLocationDao> implements IGoodsLocationService{

    @Override
    protected String doValidate(GoodsLocation entity) {
        return null;
    }
}
