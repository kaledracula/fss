package com.cony.web.controller;


import com.cony.data.jpa.entity.BaseEntity;
import com.cony.data.jpa.repository.IDao;
import com.cony.web.common.consts.Consts;
import com.cony.web.common.result.DefaultResult;
import com.cony.web.common.result.Result;
import com.cony.web.exception.DefaultExceptionHandler;
import com.cony.web.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

public class AbstractSpringController<T extends BaseEntity, Service extends IService<T, ? extends IDao<T>>>
        implements InitializingBean, ApplicationContextAware {
    /**
     * 日志，子类可以直接使用。
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 该业务关联的域对象对应的service。
     */
    private Service service;
    /**
     * context回调，可以获取spring上下文中的bean。
     */
    private ApplicationContext context;
    /**
     * 当前操作的域对象的类型。
     */
    private Class<T> entityClass;

    /**
     * 异常处理器
     */
    @Autowired
    private DefaultExceptionHandler handleException;

    /**
     * spring回调接口，设置应用上下文。
     *
     * @param applicationContext 当前spring应用上下文
     * @see ApplicationContextAware#setApplicationContext(ApplicationContext)
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.context = applicationContext;
    }

    /**
     * 获取应用上下文
     *
     * @return ApplicationContext
     */
    protected ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 获取业务逻辑层对象。
     *
     * @return 业务逻辑层对象
     */
    protected Service getService() {
        return service;
    }

    /**
     * 获取异常处理器
     *
     * @return DefaultExceptionHandler
     */
    protected DefaultExceptionHandler getExceptionHandler() {
        return handleException;
    }

    /**
     * 获取域对象的类型。
     *
     * @return 域对象的类型
     */
    public Class<T> getEntityClass() {
        return entityClass;
    }

    /**
     * 使用spring的生命周期回调，自动将依赖的service进行注入，省去子类中注入的操作。
     *
     * @throws Exception 容器初始化时的操作异常
     */
    @SuppressWarnings("unchecked")
    @Override
    public final void afterPropertiesSet() throws Exception {
        try {
            entityClass = (Class<T>) ((ParameterizedType) this.getClass()
                    .getGenericSuperclass()).getActualTypeArguments()[0];
            // 使用Service类型注入
            Class<?> serviceClazz = (Class<?>) ((ParameterizedType) this
                    .getClass().getGenericSuperclass())
                    .getActualTypeArguments()[1];
            service = (Service) this.getApplicationContext().getBean(serviceClazz);
        } catch (Exception e) {
            Class<?> clazz = null;
            try {
                clazz = (Class<?>) ((ParameterizedType) this.getClass()
                        .getGenericSuperclass()).getActualTypeArguments()[1];
                Map<String, ?> beans = this.getApplicationContext().getBeansOfType(clazz);
                if (beans != null && beans.size() > 0) {
                    service = (Service) beans.entrySet().iterator().next()
                            .getValue();
                } else {
                    logger.warn("Service依赖组件自动注入失败，按类型未找到对应的依赖{}", clazz);
                }
            } catch (Exception e1) {
                logger.warn("不使用泛型" + this.getClass());
            }
        }

    }

    /**
     * 通过ID查询域对象,子类复写doGet进行查询方法定制。
     *
     * @param id 域对象ID
     * @return Result<T>
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result get(@PathVariable Long id) {
        return doGet(id);
    }

    /**
     * 通过ID查询域对象的具体实现,
     *
     * @param id 域对象ID
     * @return Result<T>
     */
    protected Result doGet(Long id) {
        return new DefaultResult<T>().setData(getService().get(id));
    }

    /**
     * 通过ID数组查询域对象,子类复写doGetAllByIds进行查询方法定制。
     *
     * @param ids 域对象ID
     * @return Result
     */
    @RequestMapping(value = "/getAll/{ids}", method = RequestMethod.GET)
    public Result getAllByIds(@PathVariable Long[] ids) {
        return doGetAllByIds(ids);
    }

    /**
     * 通过ID数组查询域对象的具体实现
     *
     * @param ids 域对象ID
     * @return Result
     */
    protected Result doGetAllByIds(Long[] ids) {
        return new DefaultResult<List<T>>().setData(getService().get(ids));
    }

    /**
     * 获取全部域对象,子类复写doGetAll进行查询方法定制。
     *
     * @return Result
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Result getAll() {

        return doGetAll();
    }

    /**
     * 获取全部域对象的具体实现
     *
     * @return Result>
     */
    protected Result doGetAll() {
        return new DefaultResult<List<T>>().setData(getService().getAll());
    }

    /**
     * 分页查询域对象，,子类复写doQueryForPage进行查询方法定制。
     *
     * @param params 参数集合
     *               域对象ID
     * @return Result
     */
    @RequestMapping(value = "/queryForPage", method = RequestMethod.POST)
    public Result queryForPage(
            @RequestParam(value = "offSet", required = false, defaultValue = Consts.DEFAULT_OFFSET + "") int offSet,
            @RequestParam(value = "pageSize", required = false, defaultValue = Consts.DEFAULT_PAGE_SIZE + "") int pageSize,
            @RequestParam(required = false) Map<String, ? extends Object> params) {
        return doQueryForPage(offSet, pageSize, params);
    }

    /**
     * 查询域对象，子类复写doQuery进行查询方法定制。
     *
     * @param params 参数集合
     * @return Result 结果
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public Result query(@RequestParam(required = false) Map<String, ? extends Object> params) {
        return doQuery(params);
    }

    /**
     * 查询域对象的具体实现
     *
     * @param params 参数集合
     * @return Result 结果
     */
    @SuppressWarnings("unchecked")
    protected Result doQuery(Map<String, ? extends Object> params) {
        return new DefaultResult<List<T>>().setData(getService().query(params));
    }

    /**
     * 分页查询域对象的具体实现
     *
     * @param params 参数集合
     *               域对象ID
     * @return Result
     */
    @SuppressWarnings("unchecked")
    protected Result doQueryForPage(int offSet, int pageSize,
                                    Map<String, ? extends Object> params) {
        return new DefaultResult<Page<T>>().setData(getService().queryForPage(offSet, pageSize, params));
    }

    /**
     * add域对象的通用接口,子类复写doSave进行保存方法的定制。
     *
     * @param entity 域对象
     * @return Result
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result add(@Valid @RequestBody T entity) {
        return new DefaultResult().setData(doAdd(entity));
    }

    /**
     * add域对象的具体实现
     *
     * @param entity 域对象
     */
    protected T doAdd(T entity) {
        if(getService().validate(entity)) {
            return getService().add(entity);
        }
        return null;
    }

    /**
     * addAll域对象的通用接口,子类复写doSaveALL进行保存方法的定制。
     *
     * @param entities 域对象集合
     * @return Result
     */
    @RequestMapping(value = "/addAll", method = RequestMethod.POST)
    public Result addAll(@Valid @RequestBody List<T> entities) {
        return new DefaultResult().setData(doAddAll(entities));
    }

    /**
     * addALL域对象的具体实现
     *
     * @param entities 域对象集合
     */
    protected List<T> doAddAll(List<T> entities) {
        if(getService().validateAll(entities)) {
           return getService().add(entities);
        }
        return  null;
    }

    /**
     * update域对象的通用接口,子类复写doUpdate进行更新方法的定制。
     *
     * @param entity 域对象
     * @return Result
     */
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public Result update(@Valid @RequestBody T entity) throws NoSuchFieldException, IllegalAccessException {

        return new DefaultResult().setData(doUpdate(entity));
    }

    /**
     * update域对象的具体实现
     *
     * @param entity 域对象
     */
    protected T doUpdate(T entity) throws NoSuchFieldException, IllegalAccessException {
        if(getService().validate(entity)) {
            return getService().update(entity);
        }
        return null;
    }

    /**
     * updateAll域对象的通用接口,子类复写doUpdateAll进行保存方法的定制。
     *
     * @param entities 域对象集合
     * @return Result
     */
    @RequestMapping(value = "/updateAll", method = RequestMethod.PUT)
    public Result updateAll(@Valid @RequestBody List<T> entities) throws NoSuchFieldException, IllegalAccessException {

        return new DefaultResult().setData( doUpdateAll(entities));
    }

    /**
     * updateAll域对象的具体实现
     *
     * @param entities 域对象集合
     */
    protected List<T> doUpdateAll(List<T> entities) throws NoSuchFieldException, IllegalAccessException {
        if(getService().validateAll(entities)) {
            return getService().update(entities);
        }
        return null;
    }

    /**
     * 通过ID删除域对象的通用接口,子类复写doDelete进行更新方法的定制。
     *
     * @param id 域对象ID
     * @return Result
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Long id) {
        doDelete(id);
        return new DefaultResult();
    }

    /**
     * 通过ID删除域对象的具体实现
     *
     * @param id 域对象ID
     */
    protected void doDelete(Long id) {
        getService().delete(id);
    }

    /**
     * 通过ID数组删除域对象的通用接口,子类复写doDeleteAll进行更新方法的定制。
     *
     * @param ids 域对象ID集合
     * @return Result
     */
    @RequestMapping(value = "/deleteAll/{ids}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable Long[] ids) {
        doDeleteAll(ids);
        return new DefaultResult();
    }

    /**
     * 通过ID数组删除域对象的具体实现。
     *
     * @param ids 域对象ID集合
     */
    private void doDeleteAll(Long[] ids) {
        getService().delete(ids);
    }

    /**
     * 校验域对象的值是否符合数据完整性规则
     *
     * @param entity 域对象
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public Result validate(@Valid @RequestBody T entity) {
        return new DefaultResult();
    }

    /**
     * 校验多个域对象的值是否符合数据完整性规则
     *
     * @param entities 域对象集合
     */
    @RequestMapping(value = "/validateAll", method = RequestMethod.POST)
    public Result validateAll(@Valid @RequestBody List<T> entities) {
        return new DefaultResult();
    }

}
