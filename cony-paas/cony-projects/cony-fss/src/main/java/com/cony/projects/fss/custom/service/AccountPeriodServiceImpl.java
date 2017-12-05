package com.cony.projects.fss.custom.service;

import com.cony.projects.fss.custom.dao.IAccountPeriodDao;
import com.cony.projects.fss.custom.entity.AccountPeriod;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：AccountPeriod 服务实现层
*/
@Service
public class AccountPeriodServiceImpl extends AbstractService<AccountPeriod,IAccountPeriodDao> implements IAccountPeriodService{

    @Override
    protected String doValidate(AccountPeriod entity) {
        return null;
    }
}
