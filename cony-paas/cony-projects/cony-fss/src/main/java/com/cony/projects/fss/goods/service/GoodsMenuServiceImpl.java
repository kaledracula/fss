package com.cony.projects.fss.goods.service;

import com.cony.projects.fss.goods.dao.IGoodsMenuDao;
import com.cony.projects.fss.goods.entity.GoodsMenu;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：GoodsMenu 服务实现层
*/
@Service
public class GoodsMenuServiceImpl extends AbstractService<GoodsMenu,IGoodsMenuDao> implements IGoodsMenuService {

    @Override
    protected String doValidate(GoodsMenu entity) {
        return null;
    }
}
