package com.cony.data.common.utils;

import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 */
//
public class EnumUtils {
    private static final String REGEX_SEPARATOR = ",";

    private static <T extends Enum<T>> List<T> toEnumListFromIndex(Class<T> enumType, String indexsStr) {
        List<T> enumList = new ArrayList<>();
        if (StringUtils.isEmpty(indexsStr)) return enumList;
        List<T> enumValues = enumValues(enumType);
        String[] indexStrArray = indexsStr.split(REGEX_SEPARATOR);
        for (String indexStr : indexStrArray) {
            int index = Integer.parseInt(indexStr);
            T item = enumValues.get(index);
            if (enumList.contains(item)) continue;
            enumList.add(item);
        }
        return enumList;
    }

    private static <T extends Enum<T>> List<T> toEnumListFromValue(Class<T> enumType, String values) {
        List<T> list = new ArrayList<>();
        if (StringUtils.isEmpty(values)) return null;
        String[] valueArray = values.split(REGEX_SEPARATOR);
        for (String value : valueArray) {
            T item = T.valueOf(enumType, value);
            if (list.contains(item)) continue;
            list.add(item);
        }
        return list;
    }

    private static <T extends Enum<T>> T toEnum(Class<T> enumType, String value) {
        if (StringUtils.isEmpty(value)) return null;
        return T.valueOf(enumType, value);
    }


    private static <T extends Enum<T>> List<T> enumValues(Class<T> enumType) {
        List<T> list = new ArrayList<>();
        try {
            Object array = enumType.getMethod("values").invoke(null);
            int length = java.lang.reflect.Array.getLength(array);
            for (int i = 0; i < length; i++) {
                list.add((T) java.lang.reflect.Array.get(array, i));
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return list;
    }
}
