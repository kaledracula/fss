package com.cony.projects.fss.basic.service;

import com.cony.projects.fss.basic.dao.IDistributionLocationDao;
import com.cony.projects.fss.basic.entity.DistributionLocation;
import com.cony.web.service.impl.AbstractService;
import org.springframework.stereotype.Service;

/**
* 描述：DistributionLocation 服务实现层
*/
@Service
public class DistributionLocationServiceImpl extends AbstractService<DistributionLocation,IDistributionLocationDao> implements IDistributionLocationService{

    @Override
    protected String doValidate(DistributionLocation entity) {
        return null;
    }
}
