package com.cony.data.common.utils;

import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;


/**
 */
public class TreePathUtils {

    private static final String SEPERATOR = "/";

    /**
     * 构建TreePath
     *
     * @param parentId       父类ID
     * @param parentTreePath 父类TreePath
     * @return String 返回TreePath
     */
    public static String buildPath(Long parentId, String parentTreePath) {
        if (StringUtils.isEmpty(parentId)) {
            return SEPERATOR;
        } else {
            return new StringBuffer().append(parentTreePath).append(parentId).append(SEPERATOR).toString();
        }
    }

    /**
     * 根据TreePath获取祖先节点ID数组
     *
     * @param treePath TreePath
     * @return Long[] 返回祖先节点ID数组
     */
    public static List<Long> getAncientIds(String treePath) {
        if (treePath == null) {
            return null;
        } else {
            String[] segments = treePath.split(SEPERATOR);
            //Long[] result = new Long[segments.length - 1];
            List<Long> ret = new ArrayList<>(segments.length - 1);
            int length = segments.length - 1;
            for (int i = 1; i <= length; i++) {
                //result[i - 1] = Long.valueOf(segments[i]);
                ret.add(Long.valueOf(segments[i]));
            }
            return ret;
        }
    }

    /**
     * 根据TreePath获取祖先节点ID数组
     *
     * @param treePath TreePath
     * @return Long[] 返回祖先节点ID数组
     */
    public static Long[] getAncientIdsToArray(String treePath) {
        if (treePath == null) {
            return null;
        } else {
            String[] segments = treePath.split(SEPERATOR);
            Long[] result = new Long[segments.length - 1];
            int length = segments.length - 1;
            for (int i = 1; i <= length; i++) {
                result[i - 1] = Long.valueOf(segments[i]);
            }
            return result;
        }
    }

    public static String changeAncient(String path, String fromAncient, String toAncient) {
        Assert.notNull(path);
        Assert.isTrue(isDescendant(path, fromAncient), "指定的fromPath非法");
        Assert.isTrue(!isDescendant(toAncient, path), "指定的toPath非法");
        return toAncient + path.substring(fromAncient.length());
    }

    /**
     * 判断是否祖先节点
     *
     * @param path    路径
     * @param ancient 祖先路径
     * @return boolean 是否祖先节点
     */
    public static boolean isDescendant(String path, String ancient) {
        return (!path.equals(ancient)) && (path.startsWith(ancient));
    }

    /**
     * 判断是否子孙节点
     *
     * @param path       路径
     * @param descendant 子孙路径
     * @return boolean 是否子孙节点
     */
    public static boolean isAncient(String path, String descendant) {
        Assert.notNull(path);
        Assert.notNull(descendant);
        return isDescendant(descendant, path);
    }

    /**
     * 获取分隔符
     *
     * @return String 分隔符
     */
    public static String getSeperator() {
        return SEPERATOR;
    }
}
