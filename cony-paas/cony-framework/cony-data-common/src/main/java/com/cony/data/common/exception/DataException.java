package com.cony.data.common.exception;


import com.cony.context.exception.BusinessException;

/**
 * Created by wangk-p on 2017/4/25.
 */
public class DataException extends BusinessException {
    public DataException(String message) {
        super(message);
    }

}
