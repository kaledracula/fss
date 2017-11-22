package com.cony.web.service.impl;


import com.cony.data.jpa.entity.TreeEntity;
import com.cony.data.jpa.repository.ITreeDao;
import com.cony.web.service.ITreeService;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

/**
 * Created by dongb-a on 2017/5/12.
 */
@Transactional()
public abstract class AbstractTreeService<T extends TreeEntity, Dao extends ITreeDao<T>> extends AbstractService<T,Dao> implements ITreeService<T,Dao> {

    /**
     * 根据id获取父对象
     *
     * @param id 指定查询对象的Id
     * @return T 找到返回对象
     */
    @Override
    public T findParent(Serializable id) {
        return getDao().findParent(id);
    }
    /**
     * 查询对象子节点
     *
     * @param id 查询对象ID
     * @return List 查询对象列表
     */
    @Override
    public List<T> findChildren(Serializable id) {
        return getDao().findChildren(id);
    }
    /**
     * 查询对象祖先节点
     *
     * @param id 查询对象ID
     * @param includeSelf 是否包含本身
     * @return List 查询对象列表
     */
    @Override
    public List<T> findAncients(Serializable id, boolean includeSelf) {
        return getDao().findAncients(id,includeSelf);
    }
    /**
     * 查询对象子类节点
     *
     * @param id 查询对象ID
     * @param includeSelf 是否包含本身
     * @return List 查询对象列表
     */
    @Override
    public List<T> findDescendants(Serializable id, boolean includeSelf) {
        return getDao().findDescendants(id,includeSelf);
    }
    /**
     * 是否叶节点
     *
     * @param id 查询对象ID
     * @return boolean 是否叶节点
     */
    @Override
    public boolean isLeaf(Serializable id) {
        return getDao().isLeaf(id);
    }
    /**
     * 是否根节点
     *
     * @param id 查询对象ID
     * @return boolean 是否根节点
     */
    @Override
    public boolean isRoot(Serializable id) {
        return getDao().isRoot(id);
    }
    /**
     * 是否父子节点
     *
     * @param son_id 查询对象ID
     * @param parent_id 查询对象ID
     * @return boolean 是否根节点
     */
    @Override
    public boolean isChild(Serializable son_id,Serializable parent_id) {
        return getDao().isChild(son_id,parent_id);
    }

}
