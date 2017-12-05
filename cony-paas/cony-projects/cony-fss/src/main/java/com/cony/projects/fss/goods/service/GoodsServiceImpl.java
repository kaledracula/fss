package com.cony.projects.fss.goods.service;

import com.cony.projects.fss.goods.dao.IGoodsDao;
import com.cony.projects.fss.goods.entity.Goods;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：Goods 服务实现层
*/
@Service
public class GoodsServiceImpl extends AbstractService<Goods,IGoodsDao> implements IGoodsService{

    @Override
    protected String doValidate(Goods entity) {
        return null;
    }
}
