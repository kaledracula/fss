package com.cony.data.jpa.repository;

import com.cony.data.common.utils.TreePathUtils;
import com.cony.data.jpa.entity.TreeEntity;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

;

/**
 * Created by dongb-a on 2017/5/12.
 */

public abstract class AbstractTreeJpaRepository<T extends TreeEntity> extends AbstractJpaRepository<T> implements ITreeDao<T> {

    private static final String TREE_FLAG = "_tree_";
    private static final String CHILDREN = "children_";
    private static final String DESCENDANTS = "descendants_";
    /**
     * 通用新增接口。
     *
     * @param entity 指定要保存的对象
     * @return 当前保存的对象
     */
    @Override
    public T add(T entity) {
        Long parentId = entity.getParentId();
        String treePath;
        if(StringUtils.isEmpty(parentId)) {
            treePath = TreePathUtils.buildPath(parentId,null);
        } else {
            T parentEntity = get(parentId);
            treePath = TreePathUtils.buildPath(parentId,parentEntity.getTreePath());
        }
        entity.setTreePath(treePath);
        super.add(entity);
        return entity;
    }

    /**
     * 通用删除接口。
     *
     * @param entity 指定要删除的对象
     */
    @Override
    public void delete(T entity) {
        if (entity == null) {
            throw new IllegalArgumentException();
        }
        delete(entity.getId(),true);
    }
    /**
     * 删除当前实体类的一个实例。
     *
     * @param id 指定要删除的域对象id
     * @param ignoreMissing 未找到是否抛出异常
     */
    @Override
    public void delete(Serializable id,boolean ignoreMissing) {
        List<T> entityList = findDescendants(id,true);
        List<T> ancientEntityList = findAncients(id,false);
        if(entityList != null && entityList.size() > 0) {
            for (T t : entityList) {
                super.delete(t.getId(), ignoreMissing);
            }
        }
    }
    /**
     * 根据id获取父对象
     *
     * @param id 指定查询对象的Id
     * @return T 找到返回对象
     */
    @Override
    public T findParent(Serializable id) {
            T entity = get(id);
            Long parentId = entity.getParentId();
            if (StringUtils.isEmpty(parentId)) {
                return null;
            }
            return get(parentId);
    }
    /**
     * 查询对象子节点
     *
     * @param id 查询对象ID
     * @return List 查询对象列表
     */
    @Override
    public List<T> findChildren(Serializable id) {
        QueryBuilder queryBuilder = QueryBuilder.forClass(getEntityClass(), getEntityManager());
        queryBuilder.eq("parentId",id);
        bindQueryBuilderWithParams(null, queryBuilder);
        return getEntityManager().createQuery(queryBuilder.newCriteriaQuery()).getResultList();
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
        T entity = super.get(id);
        List entityList  = new ArrayList<T>();;
        if(entity != null) {
            Long[] ancientIds = TreePathUtils.getAncientIdsToArray(entity.getTreePath());
            entityList = super.get(ancientIds);
            if (includeSelf) {
                entityList.add(entity);
            }
        }
        return entityList;
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
        T entity = super.get(id);
        List entityList = new ArrayList();
        if(entity != null) {
            QueryBuilder queryBuilder = QueryBuilder.forClass(getEntityClass(), getEntityManager());
            queryBuilder.like("treePath", entity.getTreePath() + id + "%");
            bindQueryBuilderWithParams(null, queryBuilder);
            entityList = getEntityManager().createQuery(queryBuilder.newCriteriaQuery()).getResultList();
            if (includeSelf) {
                entityList.add(0, entity);
            }
        }
        return entityList;
    }



    /**
     * 是否叶节点
     *
     * @param id 查询对象ID
     * @return boolean 是否叶节点
     */
    @Override
    public boolean isLeaf(Serializable id) {
        List entityList = findChildren(id);
        return !(entityList != null && entityList.size() > 0);
    }
    /**
     * 是否根节点
     *
     * @param id 查询对象ID
     * @return boolean 是否根节点
     */
    @Override
    public boolean isRoot(Serializable id) {
        T entity = get(id);
        return !(entity.getParentId() != null && !"".equals(entity.getParentId()));
    }
    /**
     * 是否父子节点
     *
     * @param son_id 查询对象ID
     * @param parent_id 查询对象ID
     * @return boolean 是否根节点
     */
    @Override
    public boolean isChild(Serializable son_id,Serializable parent_id){
        T son = get(son_id);
        T parent = get(parent_id);
        return TreePathUtils.isDescendant(son.getTreePath()+son.getId(),parent.getTreePath()+parent.getId());
    }

    /**
     * 改变对象序号
     *
     * @param id 对象ID
     * @param changeId 交换对象ID
     */
    @Override
    public void shift(Long id, Long changeId) {
        T entity = get(id);
        T changeEntity = get(changeId);
        Long tempOd = entity.getOd();
        entity.setOd(changeEntity.getOd());
        changeEntity.setOd(tempOd);
    }

}


