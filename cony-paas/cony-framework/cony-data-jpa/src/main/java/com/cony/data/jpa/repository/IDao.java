package com.cony.data.jpa.repository;

import com.cony.data.jpa.entity.BaseEntity;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 数据操作顶层接口，与具体的实现和持久化策略分离。<br/>
 * 提供数据访问的通用接口，框架提供使用hibernate的默认实现。<br/>
 *
 * @param <T> 使用该dao操作的域对象的类型
 * @author wangkan
 * @version 1.0
 */
public interface IDao<T extends BaseEntity> {

    /**
     * 根据id获取对象。
     *
     * @param id 指定查询对象的Id
     * @return T 找到返回对象 否则返回null
     */
    T get(Serializable id);

    /**
     * 获取全部对象
     *
     * @return List 返回对象集合
     */
    List<T> getAll();

    /**
     * 根据ids获取对象。
     *
     * @param ids 指定查询对象的Id
     * @return List 找到返回集合
     */
    List<T> get(Serializable[] ids);

    /**
     * 新增当前实体类的一个实例。
     *
     * @param entity 指定要保存的对象
     * @return T 当前保存的对象
     */
    T add(T entity);

    /**
     * 批量新增，用于批量新增列表指定的对象。
     *
     * @param entities 指定要保存的对象列表
     * @return List 当前保存的对象
     */
    List<T> add(Collection<T> entities);

    /**
     * 更新当前实体类的一个实例。
     *
     * @param entity 指定要更新的对象
     * @return List 当前更新的对象
     */
    T update(T entity) throws NoSuchFieldException, IllegalAccessException;

    /**
     * 批量更新，用于批量更新列表指定的对象。
     *
     * @param entities 指定要更新的对象列表
     * @return List 当前更新的对象
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
     * @param id 指定要删除的域对象id
     */
    void delete(Serializable id);

    /**
     * 删除当前实体类的一个实例。
     *
     * @param id 指定要删除的域对象id
     * @param ignoreMissing 未找到是否抛出异常
     */
    void delete(Serializable id, boolean ignoreMissing);

    /**
     * 批量删除 ，批量删除当前id列表的指定的对象。
     *
     * @param ids 指定要删除的域对象id列表
     */
    void delete(Serializable[] ids);
    /**
     * 批量删除 ，批量删除当前id列表的指定的对象。
     *
     * @param ids 指定要删除的域对象id列表
     * @param ignoreMissing 未找到是否抛出异常
     */
    void delete(Serializable[] ids, boolean ignoreMissing);

    /**
     * 批量删除 ，批量删除当前列表的指定的对象。
     *
     * @param entities 指定要删除的域对象列表
     */
    void delete(T[] entities);

    /**
     * 批量删除,批量删除当前列表的指定的对象。
     *
     * @param entities 指定要删除的域对象列表
     */
    void delete(Collection<T> entities);



    /**
     * 查询单个记录，用于根据指定属性查询单个对象。
     *
     * @param params 查询参数
     * @return T 根据指定属性查询单个对象
     */
    T queryUnique(Map<String, ? extends Object> params);

    /**
     * 查询对象列表。
     * <strong>查询时使用查询参数的方式，以提高查询效率。子类如果有自定义查询逻辑，需实现抽象方法</strong>
     *
     * @param params 查询参数
     * @return T 查询对象列表
     */
    List<T> query(Map<String, ? extends Object> params);

    /**
     * 分页查询对象，根据前台传递的查询参数进行查询语句的自动生成，进行分页查询返回当前页的数据。<br>
     * <strong>查询时使用查询参数的方式，以提高查询效率。子类如果有自定义查询逻辑，需实现抽象方法</strong>
     *
     * @param offSet 当前记录位置
     * @param pageSize  页面大小
     * @param params    查询参数
     * @return Page 分页查询的当前页对象结果
     */
    Page queryForPage(int offSet, int pageSize, Map<String, ? extends Object> params);

    /**
     * 按属性查询单个对象
     *
     * @param propertyName 属性名
     * @param value 属性值
     * @return 域对象
     */
     T validateUnique(String propertyName, Object value);

}
