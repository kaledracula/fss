package com.cony.web.controller;

import com.cony.data.jpa.entity.TreeEntity;
import com.cony.data.jpa.repository.ITreeDao;
import com.cony.web.common.result.DefaultResult;
import com.cony.web.common.result.Result;
import com.cony.web.service.ITreeService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by wangk-p on 2017/11/28.
 */
public class AbstractTreeSpringController<T extends TreeEntity, Service extends ITreeService<T, ? extends ITreeDao<T>>> extends AbstractSpringController<T,Service>  {



    /**
     * 根据id获取父对象
     *
     * @param id 指定查询对象的Id
     * @return Result 找到返回对象
     */
    @RequestMapping(value = "/findParent/{id}", method = RequestMethod.GET)
    public Result findParent(@PathVariable Long id) {
        return new DefaultResult<T>().setData(getService().findParent(id));
    }
    /**
     * 查询对象子节点
     *
     * @param id 查询对象ID
     * @return Result 查询对象列表
     */
    @RequestMapping(value = "/findChildren/{id}", method = RequestMethod.GET)
    public Result findChildren(@PathVariable Long id) {
        return  new DefaultResult<List<T>>().setData(getService().findChildren(id));
    }
    /**
     * 查询对象祖先节点
     *
     * @param id 查询对象ID
     * @param includeSelf 是否包含本身
     * @return Result 查询对象列表
     */
    @RequestMapping(value = "/findAncients/{id}", method = RequestMethod.GET)
    public Result findAncients(@PathVariable Long id, @RequestParam(required = false,defaultValue = "false") boolean includeSelf) {
        return  new DefaultResult<List<T>>().setData(getService().findAncients(id,includeSelf));
    }
    /**
     * 查询对象子类节点
     *
     * @param id 查询对象ID
     * @param includeSelf 是否包含本身
     * @return Result 查询对象列表
     */
    @RequestMapping(value = "/findDescendants/{id}", method = RequestMethod.GET)
    public Result findDescendants(@PathVariable Long id, @RequestParam(required = false,defaultValue = "false") boolean includeSelf) {
        return  new DefaultResult<List<T>>().setData(getService().findDescendants(id,includeSelf));
    }
    /**
     * 是否叶节点
     *
     * @param id 查询对象ID
     * @return Result 是否叶节点
     */
    @RequestMapping(value = "/isLeaf/{id}", method = RequestMethod.GET)
    public Result isLeaf(@PathVariable Long id) {
        return  new DefaultResult<Boolean>().setData(getService().isLeaf(id));
    }
    /**
     * 是否根节点
     *
     * @param id 查询对象ID
     * @return Result 是否根节点
     */
    @RequestMapping(value = "/isRoot/{id}", method = RequestMethod.GET)
    public Result isRoot(@PathVariable Long id) {
        return  new DefaultResult<Boolean>().setData(getService().isRoot(id));
    }
    /**
     * 是否父子节点
     *
     * @param sonId 查询对象ID
     * @param parentId 查询对象ID
     * @return Result 是否根节点
     */
    @RequestMapping(value = "/isChild", method = RequestMethod.GET)
    public Result isChild(@RequestParam Long sonId, @RequestParam Long parentId) {
        return  new DefaultResult<Boolean>().setData(getService().isChild(sonId,parentId));
    }

    /**
     * 校验域对象的值是否符合数据完整性规则
     *
     * @param entity 域对象
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public Result validate(@RequestBody T entity) {
        getService().validate(entity);
        return new DefaultResult();
    }

    /**
     * 获取Ancients路径名称
     *
     * @param id 域对象ID
     */
    @RequestMapping(value = "/getAncientsNamePath/{id}", method = RequestMethod.POST)
    public Result getAncientsNamePath(@PathVariable Long id) {
        return new DefaultResult().setData(getService().getAncientsNamePath(id));
    }
}
