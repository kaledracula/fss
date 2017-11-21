package com.cony.data.common.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.spi.MatchingStrategy;
import org.springframework.data.domain.Page;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiPredicate;

/**
 * Created by dongb-a on 2017/5/15.
 */
public class MapUtils {
    public static <T, S> T map(S source, Class<T> targetClass) {
        if (source == null)
            return null;
        ModelMapper mapper = new ModelMapper();
        //zhul-a
        return map(source, targetClass, null);
    }


    public static <T, S> T map(S source, Class<T> targetClass, MatchingStrategy matchingStrategy) {
        if (source == null)
            return null;
        ModelMapper mapper = new ModelMapper();
        if (matchingStrategy != null)
            mapper.getConfiguration().setMatchingStrategy(matchingStrategy);
        return mapper.map(source, targetClass);
    }


    public static <T, S> List<T> mapList(List<S> sourceList, Class<T> targetItemClass) {
        if (sourceList == null)
            return null;
        ModelMapper mapper = new ModelMapper();
        List<T> result = new ArrayList<>();
        for (S sourceItem : sourceList) {
            T resultItem = mapper.map(sourceItem, targetItemClass);
            result.add(resultItem);
        }
        return result;
    }

    public static <T, S> Page<T> mapPage(Page<S> sourcePage, Class<T> targetItemClass) {
        if (sourcePage == null)
            return null;
        return sourcePage.map(s -> map(s, targetItemClass));
    }


    public static <T, S> void assign(S source, T target) {
        ModelMapper mapper = new ModelMapper();
        mapper.map(source, target);
    }

    public static <T, S> void assignList(List<S> sourceList, List<T> targetList, Class<T> targetItemClass, BiPredicate<S, T> predicate) {
        ModelMapper mapper = new ModelMapper();
        List<T> additionalList = new ArrayList<T>();
        for (S sourceItem : sourceList) {
            T bingo = matchTarget(sourceItem, targetList, predicate);
            if (bingo != null) {
                mapper.map(sourceItem, bingo);
            } else {
                T newItem = MapUtils.map(sourceItem, targetItemClass);
                additionalList.add(newItem);
            }
        }
        targetList.addAll(additionalList);
    }

    private static <T, S> T matchTarget(S sourceItem, List<T> targetList, BiPredicate<S, T> predicate) {
        for (T targetItem : targetList) {
            if (predicate.test(sourceItem, targetItem)) {
                return targetItem;
            }
        }
        return null;
    }
}
