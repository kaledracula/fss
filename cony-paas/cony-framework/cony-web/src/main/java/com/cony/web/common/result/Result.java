package com.cony.web.common.result;

/**
 * 代表某个方法的处理结果
 *
 * @author wangkan
 */
public interface Result<T> {


    public boolean isSuccess();

    public Result setResponseMessage(ResponseMessage responseMessage);

    public Result setCode(String code);

    public Result setMessage(String message);

    Result appendMessage(String message);

    public Result setData(T data);

    public String getCode();

    public String getMessage();

    public T getData();
}

