package com.cony.context.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 用于在容器管理的bean之外获取多spring容器及其管理的bean。
 * 例如在web环境或者其他引擎环境中，获取到spring上下文的bean。
 * @author wangkan
 */

@Component
public class SpringContextUtils implements ApplicationContextAware {
	
    /** Spring上下文环境 */
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAwre接口的回调方法，设置上下文环境
     * 
     * @param context
     *            spring回调时，传入的ApplicationContext
     */
    public void setApplicationContext(ApplicationContext context) {
    	SpringContextUtils.initApplicationContext(context);
    }

    /**
     * 获取ApplicationContext
     * 
     * @return 当前ApplicationContext
     */
    public static ApplicationContext getApplicationContext() {
        if (applicationContext == null)
            throw new IllegalStateException("applicaitonContext未注入!");
        return applicationContext;
    }

    /**
     * 获取指定名称的bean。
     * 
     * @param <T>
     *            指定泛型类型
     * @param name
     *            指定获取bean的名称
     * @return 指定名称的bean
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 获取类型为requiredType的对象。
     * 
     * @param <T>
     *            使用泛型指定获取对象的类型
     * @param name
     *            指定获取bean的名称
     * @param requiredType
     *            指定的类型
     * @return 类型为requiredType的对象
     */
    public static <T> T getBean(String name, Class<T> requiredType) {
        return (T) applicationContext.getBean(name, requiredType);
    }

    /**
     * 获取类型为requiredType的对象。
     * 
     * @param <T>
     *            使用泛型指定获取对象的类型
     * @param requiredType
     *            指定的类型
     * @return 类型为requiredType的对象
     */
    public static <T> T getBean(Class<T> requiredType) {
        return (T) applicationContext.getBean(requiredType);
    }

    /**
     * 如果BeanFactory包含一个与所给名称匹配的Bean定义则返回true。
     * 
     * @param name
     *            指定的bean名称
     * @return 是否包含与所给名称匹配的Bean定义
     */
    public static boolean contrainsBean(String name) {
        return applicationContext.containsBean(name);
    }

    /**
     * 判断这个Bean是singleton还是prototype
     * 
     * @param name
     *            指定bean名称
     * @return 如果指定的bean为singleton，则返回true，否则false
     */
    public static boolean isSingleton(String name) {
        return applicationContext.isSingleton(name);
    }

    /**
     * 取得指定Bean的类型。
     * 
     * @param name
     *            bean名称
     * @return 指定bean的类型
     */
    public static Class<?> getType(String name) {
        return applicationContext.getType(name);
    }

    /**
     * 取得这个Bean的所有别名。
     * 
     * @param name
     *            bean名称
     * @return 指定bean的所有别名列表
     */
    public static String[] getAliases(String name) {
        return applicationContext.getAliases(name);
    }

    /**
     * 使用静态方法初始化
     * @param ctx
     */
    private static void initApplicationContext(ApplicationContext ctx){
    	applicationContext = ctx;
    }
}
