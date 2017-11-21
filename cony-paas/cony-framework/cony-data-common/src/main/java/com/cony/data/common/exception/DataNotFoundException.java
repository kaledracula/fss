package com.cony.data.common.exception;


/**
 */
public class DataNotFoundException extends DataException {
    public static final String DEFAULT_MESSAGE = "数据不存在或已被删除";

    public DataNotFoundException() {
        this(DEFAULT_MESSAGE);
    }

    public DataNotFoundException(String message) {
        super(message);
    }
}
