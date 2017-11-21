package com.cony.data.jpa.repository;

import com.cony.data.common.exception.DataException;
import com.cony.data.common.exception.DataNotFoundException;
import com.cony.data.jpa.entity.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.repository.Repository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 数据操作父接口<br/>
 * 提供了与JPA绑定的默认实现，并且提供了一些通用的方法进行数据对象的存取。<br/>
 * 该类可以直接使用Map传递的参数拼接查询语句，支持连接查询，连接查询时需要指定查询主语句。<br/>
 *
 * @param <T> 实体类类型
 * @author wangkan
 * @version 1.0
 */
@SuppressWarnings("ALL")
public abstract class AbstractJpaRepository<T extends BaseEntity> implements IDao<T>, Repository<T, Long>, InitializingBean {
    /**
     * 日志，子类可以使用。
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private EntityManager entityManager;


    private Class<T> clazz;

    protected Class<T> getEntityClass() {
        return clazz;
    }

    protected String getEntityClassName() {
        String[] s =  clazz.getName().split("\\.");
        return s[s.length-1];
    }

    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        clazz = (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * 获取读EntityManager
     *
     * @return EntityManager
     */
    protected EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * 根据id获取域对象，如果存在 返回对应的对象；否则返回null，业务逻辑注意null的处理。
     *
     * @param id 指定查询对象的Id
     * @return 找到返回对象 否则返回null
     */
    @SuppressWarnings("unchecked")
    @Override
    public T get(Serializable id) {
        T result = entityManager.find(clazz, id);
        if(result == null) {
            throw new DataNotFoundException();
        }
        return result;
    }

    /**
     * 获取全部数据的集合；如果为空则返回null，业务逻辑注意null的处理。
     *
     * @return 返回对象集合
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> getAll() {
        QueryBuilder queryBuilder = QueryBuilder.forClass(clazz, entityManager);
        return entityManager.createQuery(queryBuilder.newCriteriaQuery()).getResultList();
    }

    /**
     * 根据ids获取域对象列表，如果存在，返回对象List；否则返回null，业务逻辑注意null的处理。
     *
     * @param ids 指定查询对象的Ids
     * @return 找到返回对象List
     */
    @SuppressWarnings("unchecked")
    @Override
    public List<T> get(Serializable[] ids) {
        QueryBuilder queryBuilder = QueryBuilder.forClass(clazz, entityManager);
        queryBuilder.in("id", ids);
        return entityManager.createQuery(queryBuilder.newCriteriaQuery()).getResultList();
    }

    /**
     * 通用新增接口。
     *
     * @param entity 指定要保存的对象
     * @return 当前保存的对象
     */
    @Override
    public T add(T entity) {
        entityManager.persist(entity);
        return entity;
    }




    /**
     * 批量保存。
     *
     * @param entities 指定要保存的对象列表
     * @return 保存的对象列表
     */
    @Override
    public List<T> add(Collection<T> entities) {
        return entities.stream()
                .map(this::add)
                .collect(Collectors.toList());
    }

    /**
     * 通用更新接口。
     *
     * @param entity 指定要更新的对象
     * @return 当前更新的对象
     */
    @Override
    public T update(T entity) throws NoSuchFieldException, IllegalAccessException {
        T updateEntity = get(entity.getId());
        this.updateEntityField(entity, updateEntity);
        entityManager.merge(updateEntity);
        return updateEntity;
    }

    /**
     * 批量更新。
     *
     * @param entities 指定要更新的对象列表
     * @return 更新的对象列表
     */
    @Override
    public List<T> update(Collection<T> entities) throws NoSuchFieldException, IllegalAccessException {
        List<T> list = new ArrayList<T>();
        for (T entity : entities) {
            list.add(update(entity));
        }
        return list;
    }

    /**
     * 通用删除接口。
     *
     * @param entity 指定要删除的对象
     */
    @Override
    public void delete(T entity) {
        entityManager.remove(entity);
    }


    /**
     * 根据id删除对象，如果id对应的对象为空，直接返回。
     *
     * @param id 指定要删除的域对象id
     */
    @Override
    public void delete(Serializable id) {
        delete(id,false);
    }

    /**
     * 删除当前实体类的一个实例。
     *
     * @param id 指定要删除的域对象id
     * @param ignoreMissing 未找到是否抛出异常
     */
    @Override
    public void delete(Serializable id,boolean ignoreMissing) {
        T t = entityManager.find(clazz, id);
        if (t == null ) {
            if(!ignoreMissing) {
                throw new DataNotFoundException();
            }
        } else {
            entityManager.remove(t);
        }
    }

    /**
     * 批量删除对象。
     *
     * @param ids 指定要删除的域对象id列表
     */
    @Override
    public void delete(Serializable[] ids) {
        if (ids == null) {
            return;
        }
        for (Serializable id : ids)
            delete(id);
    }

    /**
     * 批量删除 ，批量删除当前id列表的指定的对象。
     *
     * @param ids 指定要删除的域对象id列表
     * @param ignoreMissing 未找到是否抛出异常
     */
    @Override
    public void delete(Serializable[] ids, boolean ignoreMissing) {
        if (ids == null) {
            return;
        }
        for (Serializable id : ids)
            delete(id,ignoreMissing);
    }

    /**
     * 批量删除对象。
     *
     * @param entities 指定要删除的域对象列表
     */
    @Override
    public void delete(T[] entities) {
        for (int i = 0; i < entities.length; i++) {
            delete(entities[i]);
        }
    }

    /**
     * 批量删除对象。
     *
     * @param entities 指定要删除的域对象列表
     */
    @Override
    public void delete(Collection<T> entities) {
        if (entities == null)
            throw new IllegalArgumentException();
        entities.forEach(this::delete);
    }

    /**
     * 按属性查询单个对象
     *
     * @param propertyName 属性名
     * @param value 属性值
     * @return 域对象
     */
    @Override
    public T validateUnique(String propertyName,Object value) {
        QueryBuilder queryBuilder = QueryBuilder.forClass(clazz, entityManager);
        queryBuilder.eq(propertyName,value);
        List<T> list = entityManager.createQuery(queryBuilder.newCriteriaQuery()).getResultList();
        return list.size() >= 1?list.get(0):null;
    }
    /**
     * 查询单个对象，需实现抽象方法
     *
     * @param params 参数集合
     * @return 域对象
     */
    @Override
    public T queryUnique(Map<String, ? extends Object> params) {
        QueryBuilder queryBuilder = QueryBuilder.forClass(clazz, entityManager);
        bindQueryBuilderWithParams(params, queryBuilder);
        return (T) entityManager.createQuery(queryBuilder.newCriteriaQuery()).getSingleResult();
    }
    /**
     * 查询多个对象，需实现抽象方法
     *
     * @param params 参数集合
     * @return 域对象
     */
    @Override
    public List<T> query(Map<String, ? extends Object> params) {
        QueryBuilder queryBuilder = QueryBuilder.forClass(clazz, entityManager);
        bindQueryBuilderWithParams(params, queryBuilder);
        return entityManager.createQuery(queryBuilder.newCriteriaQuery()).getResultList();
    }

    /**
     * 分页查询的调用接口，查询语句使用参数的方式，子类如果有自定义查询逻辑，需实现抽象方法
     *
     * @param offSet 当前记录位置
     * @param pageSize  页大小
     * @param params    查询参数
     * @return 当前页的查询结果
     */
    @Override
    public Page<T> queryForPage(int offSet, int pageSize, Map<String, ? extends Object> params) {
        QueryBuilder queryBuilder = QueryBuilder.forClass(clazz, entityManager);
        bindQueryBuilderWithParams(params, queryBuilder);
        long totalCount = queryForCount(queryBuilder);
        List<T> result;
        result = entityManager.createQuery(
                    queryBuilder.newCriteriaQuery()).
                    setFirstResult(offSet).
                    setMaxResults(pageSize).
                    getResultList();
        return new PageImpl<T>(result, null, totalCount);
    }

    protected abstract void bindQueryBuilderWithParams(Map<String, ?> params, QueryBuilder queryBuilder);

    /**
     * 分页查询时，查询记录总数。
     *
     * @param queryBuilder 查询参数
     * @return 记录总数
     */
    @SuppressWarnings("unchecked")
    protected long queryForCount(QueryBuilder queryBuilder) {
        return entityManager.createQuery(queryBuilder.newCriteriaQuery()).getResultList().size();
    }




//    /**
//     * 计算当前查询的真实记录索引。
//     *
//     * @param pageIndex 当前页索引
//     * @param pageSize  页大小
//     * @return 计算当前查询的真实记录索引
//     */
////    private int calStart(int pageIndex, int pageSize) {
////        if (pageIndex < 1) {
////            return 0;
////        }
////        return (pageIndex - 1) * pageSize;
////    }

    protected void updateEntityField(T entity, T updateEntity) throws IllegalAccessException, NoSuchFieldException {
        List<Field> fieldList = getAllFiledFromSuperClass(entity);
        for (int i = 0; i < fieldList.size(); i++) {
            Field f = fieldList.get(i);
            f.setAccessible(true);
            Object val = null;
            try {
                val = f.get(entity);
            } catch (IllegalAccessException e) {
                throw new DataException(e.getMessage());
            }
            if (val != null) {
                try {
                    f.set(updateEntity, val);
                } catch (IllegalAccessException e) {
                    throw new DataException(e.getMessage());
                }
            }
        }
    }

    private List<Field> getAllFiledFromSuperClass(T entity) {
        List<Field> entityFieldList = new ArrayList<Field>();
        Class tempClass = entity.getClass();
        while (tempClass != Object.class) {//当父类为null的时候说明到达了最上层的父类(Object类).
            entityFieldList.addAll(Arrays.asList(tempClass.getDeclaredFields()));
            tempClass = tempClass.getSuperclass(); //得到父类,然后赋给自己
        }
        return entityFieldList;
    }

    protected boolean isNullOrEmpty(Map<String, ?> params, String key) {
        if (params == null) {
            return true;
        }
        Object value = params.get(key);
        if (value instanceof String) {
            return value == null || "".equals(value);
        }
        return value == null;
    }

}
