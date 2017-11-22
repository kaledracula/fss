package com.cony.web.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * 返回值的默认实现
 *
 * @author wangkan
 */

public class DefaultResult<T> implements Result<T> {

    private String code = ResponseMessage.Success.getCode();
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String message = "success";
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;


    @Override
    public boolean isSuccess() {
        return ResponseMessage.Success.getCode() == code;
    }

    @Override
    public Result setResponseMessage(ResponseMessage responseMessage) {
        this.code = responseMessage.getCode();
        this.message = responseMessage.getMessage();
        return this;
    }

    @Override
    public Result setCode(String code) {
        this.code = code;
        return this;
    }

    @Override
    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public Result appendMessage(String message) {
        this.message = this.message + message;
        return this;
    }

    @Override
    public Result setData(T data) {
        this.data = data;
        return this;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public T getData() {
        return data;
    }
}
