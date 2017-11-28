package com.cony.projects.fss.goods.service;

import com.cony.projects.fss.goods.dao.ICategoryDao;
import com.cony.projects.fss.goods.entity.Category;
import com.cony.web.service.impl.AbstractTreeService;
import org.springframework.stereotype.Service;

/**
* 描述：Category 服务实现层
*/
@Service
public class CategoryServiceImpl extends AbstractTreeService<Category,ICategoryDao> implements ICategoryService{

    @Override
    protected String doValidate(Category entity) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(validateNameUnique(entity));
        return stringBuilder.toString();
    }

    private String validateNameUnique(Category entity) {
        return getDao().validateUnique("name",entity.getName()) == null?
                "": "名称已存在！";
    }

}
