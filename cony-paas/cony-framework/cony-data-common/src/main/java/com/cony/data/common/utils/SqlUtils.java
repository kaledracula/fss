package com.cony.data.common.utils;

/**
 */
public class SqlUtils {
    private static final String CONNECTOR_LIKE = "%";

    public static String likeJoin(String fragment) {
        return CONNECTOR_LIKE + fragment + CONNECTOR_LIKE;
    }

    public static String leftLikeJoin(String fragment) {
        return CONNECTOR_LIKE + fragment;
    }

    public static String rigthLikeJoin(String fragment) {
        return fragment + CONNECTOR_LIKE;
    }

}
