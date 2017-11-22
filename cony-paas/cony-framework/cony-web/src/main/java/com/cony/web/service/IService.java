package com.cony.web.service;

import com.cony.data.jpa.entity.BaseEntity;
import com.cony.data.jpa.repository.IDao;
import org.springframework.data.domain.Page;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 业务逻辑操作接口，为controller层提供业务操作接口。<br/>
 * IService只是提供了最为通用的业务操作接口，包括数据的增删改查操作。<br/>
 * 子类根据自己的业务逻辑添加本业务相关的接口方法，service层保证业务层方法执行在事务环境中。<br/>
 *
 * @param <T> 域对象类型
 * @param <D> Dao类型
 * @author wangkan
 * @version 1.0
 */
public interface IService<T extends BaseEntity, D extends IDao<T>> {


    /**
     * 根据id获取对象。
     *
     * @param entityId 指定查询对象的Id
     * @return 找到返回对象 否则返回null
     */
    T get(Serializable entityId);

    /**
     * 获取全部对象
     *
     * @return 返回对象集合
     */
    List<T> getAll();

    /**
     * 根据ids获取对象。
     *
     * @param ids 指定查询对象的Id
     * @return 找到返回集合
     */
    List<T> get(Serializable[] ids);

    /**
     * 保存对象。
     *
     * @param entity 指定要保存的对象
     * @return 当前保存的对象
     */
    T add(T entity);

    /**
     * 批量保存。
     *
     * @param entities 指定要保存的对象列表
     */
    List<T> add(Collection<T> entities);

    /**
     * 更新当前实体类的一个实例。
     *
     * @param entity 指定要更新的对象
     * @return 当前更新的对象
     */
    T update(T entity) throws NoSuchFieldException, IllegalAccessException;

    /**
     * 批量更新，用于批量更新列表指定的对象。
     *
     * @param entities 指定要更新的对象列表
     */
    List<T> update(Collection<T> entities) throws NoSuchFieldException, IllegalAccessException;

    /**
     * 删除指定类型的域对象。
     *
     * @param entity 指定要删除的域对象
     */
    void delete(T entity);

    /**
     * 删除当前实体类的一个实例。
     *
     * @param entityId 指定要删除的域对象id
     */
    void delete(Serializable entityId);
    /**
     * 删除当前实体类的一个实例。
     *
     * @param id 指定要删除的域对象id
     * @param ignoreMissing 未找到是否抛出异常
     */
    void delete(Serializable id, boolean ignoreMissing);

    /**
     * 批量删除 泛型批量删除接口。
     *
     * @param entityIds 指定要删除的域对象id列表
     */
    void delete(Serializable[] entityIds);
    /**
     * 批量删除 ，批量删除当前id列表的指定的对象。
     *
     * @param ids 指定要删除的域对象id列表
     * @param ignoreMissing 未找到是否抛出异常
     */
    void delete(Serializable[] ids, boolean ignoreMissing);

    /**
     * 批量删除 泛型批量删除接口。
     *
     * @param entities 指定要删除的域对象列表
     */
    void delete(T[] entities);

    /**
     * 批量删除 泛型批量删除接口。
     *
     * @param entities 指定要删除的域对象列表
     */
    void delete(Collection<T> entities);

    /**
     * 根据指定的属性，查询是否存在对象。
     *
     * @param propertyName 属性名称
     * @param value        属性值
     * @return 按指定属性查询的单个对象
     */
    T validateUnique(String propertyName, Object value);

    /**
     * 查询单个对象 以MAP为参数。
     *
     * @param params 查询参数
     * @return 根据指定属性查询得到的单个对象
     */
    T queryUnique(Map<String, ? extends Object> params);

    /**
     * 查询对象列表。
     *
     * @param params 查询对象列表
     * @return 查询结果
     */
    List<T> query(Map<String, ? extends Object> params);

    /**
     * 分页查询。
     *
     * @param offSet 当前记录位置
     * @param pageSize  页面大小
     * @param params    查询参数
     * @return 分页查询的当前页对象结果
     */
    Page queryForPage(int offSet, int pageSize,
                      Map<String, ? extends Object> params);

    /**
     * 校验对象。
     *
     * @param entity 指定要校验的对象
     * @return 校验成功或失败
     */
    Boolean validate(T entity);

    /**
     * 校验对象。
     *
     * @param entityList 指定要校验的对象
     * @return 校验结果
     */
    Boolean validateAll(List<T> entityList);


}
