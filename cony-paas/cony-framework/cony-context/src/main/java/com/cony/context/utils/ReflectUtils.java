package com.cony.context.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 */
public class ReflectUtils {
    @SuppressWarnings("unchecked")
    public static <T> T createInstance(Class<T> clazz) {
        return createInstance(clazz, new Class[] {}, new Object[] {});
    }

    @SuppressWarnings("unchecked")
    public static <T> T createInstance(Class<T> clazz, Class<?>[] parameterTypes, Object[] parameterValues) {
        try {
            Constructor<?> constructor = clazz.getConstructor(parameterTypes);
            T result = (T) constructor.newInstance(parameterValues);
            return result;
        } catch (NoSuchMethodException e) {
            throw new ReflectException(String.format("无法创建%s对象", clazz.getName()), e);
        }
        catch ( IllegalAccessException e )  {
            throw new ReflectException(String.format("无法创建%s对象", clazz.getName()), e);
        }
        catch (InstantiationException e) {
            throw new ReflectException(String.format("无法创建%s对象", clazz.getName()), e);
        }
        catch (InvocationTargetException e) {
            throw new ReflectException(String.format("无法创建%s对象", clazz.getName()), e);
        }
    }

    public static Class<?> getFirstGenericClass(Class<?> clazz) {
        return getFirstGenericClass(clazz, null);
    }

    public static Class<?> getFirstGenericClass(Class<?> clazz, Class<?> stopOnClazz) {
        Class<?>[] classes = getGenericClasses(clazz, stopOnClazz);
        return classes[0];
    }

    public static Class<?>[] getGenericClasses(Class<?> clazz) {
        return getGenericClasses(clazz, null);
    }

    public static Class<?>[] getGenericClasses(Class<?> clazz, Class<?> stopOnClazz) {
        Type genericSuperClass = getGenericSuperClass(clazz, stopOnClazz);
        if (genericSuperClass == null) {
            throw new ReflectException(String.format("无法从类%s中找到泛型定义", clazz.getName()));
        }
        Type[] parameterTypes = ((ParameterizedType) genericSuperClass).getActualTypeArguments();
        Class[] result = new Class[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] instanceof Class)
                result[i] = (Class) parameterTypes[i];
        }
        return result;
    }

    public static Type getGenericSuperClass(Class<?> clazz, Class<?> stopOnClazz) {
        Type superClassType = clazz.getGenericSuperclass();
        while (superClassType != null) {
            if (superClassType instanceof ParameterizedType) {
                return superClassType;
            }
            if (superClassType instanceof Class) {
                if (stopOnClazz != null && superClassType.equals(stopOnClazz))
                    break;
                superClassType = ((Class) superClassType).getGenericSuperclass();
            }
        }
        return null;
    }
}
