package com.cony.web.service;

import com.cony.data.jpa.entity.TreeEntity;
import com.cony.data.jpa.repository.ITreeDao;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wangk-p on 2017/5/15.
 */
public interface ITreeService<T extends TreeEntity, D extends ITreeDao<T>> extends IService<T,D>{

    /**
     * 根据id获取父对象
     *
     * @param id 指定查询对象的Id
     * @return T 找到返回对象
     */
    T findParent(Serializable id);

    /**
     * 查询对象子节点
     *
     * @param id 查询对象ID
     * @return List 查询对象列表
     */
    List<T> findChildren(Serializable id);
    /**
     * 查询对象祖先节点
     *
     * @param id 查询对象ID
     * @param includeSelf 是否包含本身
     * @return List 查询对象列表
     */
    List<T> findAncients(Serializable id, boolean includeSelf);
    /**
     * 查询对象子类节点
     *
     * @param id 查询对象ID
     * @param includeSelf 是否包含本身
     * @return List 查询对象列表
     */
    List<T> findDescendants(Serializable id, boolean includeSelf);
    /**
     * 是否叶节点
     *
     * @param id 查询对象ID
     * @return boolean 是否叶节点
     */
    boolean isLeaf(Serializable id);
    /**
     * 是否根节点
     *
     * @param id 查询对象ID
     * @return boolean 是否根节点
     */
    boolean isRoot(Serializable id);
    /**
     * 是否父子节点
     *
     * @param son_id 查询对象ID
     * @param parent_id 查询对象ID
     * @return boolean 是否根节点
     */
    boolean isChild(Serializable son_id, Serializable parent_id);
    /**
     * 获取节点名称路径
     *
     * @param id 查询对象ID
     * @return string 名称路径
     */
    String getAncientsNamePath(Long id);
}
