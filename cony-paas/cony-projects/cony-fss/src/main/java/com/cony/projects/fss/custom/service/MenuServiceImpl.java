package com.cony.projects.fss.custom.service;

import com.cony.projects.fss.custom.dao.IMenuDao;
import com.cony.projects.fss.custom.entity.Menu;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：Menu 服务实现层
*/
@Service
public class MenuServiceImpl extends AbstractService<Menu,IMenuDao> implements IMenuService{

    @Override
    protected String doValidate(Menu entity) {
        return null;
    }
}
