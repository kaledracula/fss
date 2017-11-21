package com.cony.web.service.impl;


import com.cony.data.common.exception.DataException;
import com.cony.data.jpa.entity.BaseEntity;
import com.cony.data.jpa.repository.IDao;
import com.cony.web.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;


/**
 * 业务逻辑操作接口，为controller层提供业务操作接口。<br/>
 * IService只是提供了最为通用的业务操作接口，包括数据的增删改查操作。<br/>
 * 子类根据自己的业务逻辑添加本业务相关的接口方法，service层保证业务层方法执行在事务环境中。<br/>
 *
 * @param <T>   域对象类型
 * @param <Dao> Dao类型
 * @author wangkan
 * @version 1.0
 */
@Transactional(readOnly = true)
public abstract class AbstractService<T extends BaseEntity, Dao extends IDao<T>> implements IService<T, Dao>, InitializingBean, ApplicationContextAware {

    /**
     * 日志，子类可以使用。
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 域对象对应的数据访问接口。
     */
    private Dao dao;
    /**
     * spring context用于获取该对象的依赖。
     */
    private ApplicationContext ctx;

    /**
     * 获取域对象使用的Dao。
     *
     * @return 域对象使用的Dao
     */
    public Dao getDao() {
        return dao;
    }

    /**
     * 设置域对象使用的dao。
     *
     * @param dao 域对象使用的dao
     */
    public void setDao(Dao dao) {
        this.dao = dao;
    }

    /**
     * spring回调接口，用于设置spring上下文。
     *
     * @param applicationContext 当前spring应用上下文
     * @see ApplicationContextAware#setApplicationContext(ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    /**
     * 获取Spring上下文
     *
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return ctx;
    }

    /**
     * 设置对象的初始依赖。
     *
     * @throws Exception 设置对象的初始依赖时的处理异常
     */
    @SuppressWarnings("unchecked")
    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            String beanName = ((Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1]).getSimpleName();
            beanName = StringUtils.uncapitalize(beanName);
            dao = (Dao) ctx.getBean(beanName);
        } catch (Exception e) {
            Class<?> clazz = (Class<?>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
            Map<String, ?> beans = ctx.getBeansOfType(clazz);
            if (beans != null && beans.size() > 0) {
                dao = (Dao) beans.entrySet().iterator().next().getValue();
            } else {
                logger.warn("dao依赖组件自动注入失败，按类型未找到对应的依赖{}", clazz);
            }
        }
    }

    /**
     * 根据id获取对象。
     *
     * @param entityId 指定查询对象的Id
     * @return 找到返回对象 否则返回null
     */
    @Override
    public T get(Serializable entityId) {
        return getDao().get(entityId);
    }

    /**
     * 获取全部对象
     *
     * @return 返回对象集合
     */
    @Override
    public List<T> getAll() {
        return getDao().getAll();
    }

    /**
     * 根据ids获取对象。
     *
     * @param ids 指定查询对象的Id
     * @return 找到返回集合
     */
    @Override
    public List<T> get(Serializable[] ids) {
        return getDao().get(ids);
    }

    /**
     * 保存对象。
     * t
     *
     * @param entity 指定要保存的对象
     * @return 当前保存的对象
     */
    @Override
    @Transactional()
    public T add(T entity) {
        beforeAdd(entity);
        return getDao().add(entity);
    }

    protected abstract void beforeAdd(T entity);

    /**
     * 批量保存。
     *
     * @param entities 指定要保存的对象列表
     */
    @Override
    @Transactional()
    public List<T> add(Collection<T> entities) {
        beforeAdd(entities);
        return getDao().add(entities);
    }

    protected abstract void beforeAdd(Collection<T> entities);

    /**
     * 更新当前实体类的一个实例。
     *
     * @param entity 指定要更新的对象
     * @return 当前更新的对象
     */
    @Override
    @Transactional()
    public T update(T entity) throws NoSuchFieldException, IllegalAccessException {
        beforeUpdate(entity);
        return getDao().update(entity);
    }

    protected abstract void beforeUpdate(T entities);

    /**
     * 批量更新，用于批量更新列表指定的对象。
     *
     * @param entities 指定要更新的对象列表
     */
    @Override
    @Transactional()
    public List<T> update(Collection<T> entities) throws NoSuchFieldException, IllegalAccessException {
        beforeUpdate(entities);
        return getDao().update(entities);
    }

    protected abstract void beforeUpdate(Collection<T> entities);

    /**
     * 删除指定类型的域对象。
     *
     * @param entity 指定要删除的域对象
     */
    @Override
    @Transactional()
    public void delete(T entity) {
        getDao().delete(entity);
    }

    /**
     * 删除当前实体类的一个实例。
     *
     * @param entityId 指定要删除的域对象id
     */
    @Override
    @Transactional()
    public void delete(Serializable entityId) {
        getDao().delete(entityId);
    }
    /**
     * 删除当前实体类的一个实例。
     *
     * @param id 指定要删除的域对象id
     * @param ignoreMissing 未找到是否抛出异常
     */
    @Override
    @Transactional()
    public void delete(Serializable id, boolean ignoreMissing) {
        getDao().delete(id,ignoreMissing);
    }

    /**
     * 批量删除域对象。
     *
     * @param entityIds 指定要删除的域对象id列表
     */
    @Override
    @Transactional()
    public void delete(Serializable[] entityIds) {
        getDao().delete(entityIds);
    }
    /**
     * 批量删除 ，批量删除当前id列表的指定的对象。
     *
     * @param ids 指定要删除的域对象id列表
     * @param ignoreMissing 未找到是否抛出异常
     */
    @Override
    @Transactional()
    public void delete(Serializable[] ids, boolean ignoreMissing) {
        getDao().delete(ids,ignoreMissing);
    }

    /**
     * 批量删除域对象。
     *
     * @param entities 指定要删除的域对象列表
     */
    @Override
    @Transactional()
    public void delete(T[] entities) {
        getDao().delete(entities);
    }

    /**
     * 批量删除域对象。
     *
     * @param entities 指定要删除的域对象列表
     */
    @Override
    @Transactional()
    public void delete(Collection<T> entities) {
        getDao().delete(entities);
    }

    /**
     * 根据指定的属性，查询是否存在对象。
     *
     * @param propertyName 属性名称
     * @param value        属性值
     * @return 按指定属性查询的单个对象
     */
    @Override
    public T validateUnique(String propertyName, Object value) {
        return getDao().validateUnique(propertyName,value);
    }

    /**
     * 查询单个对象 以MAP为参数。
     *
     * @param params 查询参数
     * @return 根据指定属性查询得到的单个对象
     */
    @Override
    public T queryUnique(Map<String, ?> params) {
        return getDao().queryUnique(params);
    }

    /**
     * 查询对象列表。
     *
     * @param params 查询对象列表
     * @return 查询结果
     */
    @Override
    public List<T> query(Map<String, ?> params) {
        return getDao().query(params);
    }

    /**
     * 分页查询。
     *
     * @param offSet 当前记录位置
     * @param pageSize  页面大小
     * @param params    查询参数
     * @return 分页查询的当前页对象结果
     */
    @Override
    public Page queryForPage(int offSet, int pageSize, Map<String, ?> params) {
        return getDao().queryForPage(offSet, pageSize, params);
    }

    /**
     * 校验对象。
     *
     * @param entity 指定要校验的对象
     * @return 校验结果
     */
    public Boolean validate(T entity) {
        if(entity != null) {
            String result = doValidate(entity);
            if (!StringUtils.isEmpty(result)) {
                throw new DataException(result);
            }
        }
        return true;

    }

    /**
     * 校验实体的具体实现。
     *
     * @param entity 指定要校验的对象
     * @return 校验结果
     */
    protected abstract String doValidate(T entity);

}
