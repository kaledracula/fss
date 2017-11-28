package com.cony.projects.fss.goods.service;

import com.cony.projects.fss.goods.dao.ICategoryDao;
import com.cony.projects.fss.goods.entity.Category;
import com.cony.web.service.ITreeService;

/**
* 描述：Category 服务实现层接口
*/
public interface ICategoryService extends ITreeService<Category,ICategoryDao> {

}
