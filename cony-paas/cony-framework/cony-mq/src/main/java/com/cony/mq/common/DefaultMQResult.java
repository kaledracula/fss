package com.cony.mq.common;


import org.springframework.util.StringUtils;

/**
 * Created by wangk-p on 2017/4/19.
 */
public class DefaultMQResult implements MQResult {

    private String result;

    private  String errors;

    private String messages;

    public MQResult addError(String error) {
        this.errors = error;
        return this;
    }

    public MQResult addErrorCode(String code, Object[] args, String defaultError) {
        return null;
    }

    public MQResult addMessage(String message) {
        this.messages = message;
        return this;
    }

    public MQResult addMessageCode(String code, Object[] args, String defaultMessage) {
        return null;
    }

    public MQResult clearError() {
        return null;
    }

    public MQResult clearMessage() {
        return null;
    }

    public MQResult clearAll() {
        return null;
    }

    public boolean isSuccess() {
        return StringUtils.isEmpty(errors);
    }

    public String getErrors() {
        return errors;
    }

    public String getMessages() {
        return messages;
    }
}
