package com.cony.web.exception;

import com.cony.context.exception.BusinessException;
import com.cony.data.common.exception.DataException;
import com.cony.data.common.validate.ValidateError;
import com.cony.web.common.result.DefaultResult;
import com.cony.web.common.result.ResponseMessage;
import com.cony.web.common.result.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@ControllerAdvice
@ResponseBody
public class DefaultExceptionHandler {

    /**
     * 日志，子类可以直接使用。
     */
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        logger.error("缺少请求参数", e);
        return new DefaultResult().setResponseMessage(ResponseMessage.Missing_Servlet_Request_Parameter_Exception).appendMessage(e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        logger.error("参数解析失败", e);
        return new DefaultResult().setResponseMessage(ResponseMessage.Http_Message_Not_Readable_Exception).appendMessage(e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        logger.error("参数验证失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return new DefaultResult().setResponseMessage(ResponseMessage.Method_Argument_Not_Valid_Exception).appendMessage(message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e) {
        logger.error("参数绑定失败", e);
        BindingResult result = e.getBindingResult();
        FieldError error = result.getFieldError();
        String field = error.getField();
        String code = error.getDefaultMessage();
        String message = String.format("%s:%s", field, code);
        return new DefaultResult().setResponseMessage(ResponseMessage.Bind_Exception).appendMessage(message);
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleServiceException(ConstraintViolationException e) {
        logger.error("参数验证失败", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        StringBuilder stringBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : violations) {
            violation = violations.iterator().next();
            stringBuilder.append(violation.getMessage());
        }
        return new DefaultResult().setResponseMessage(ResponseMessage.Constraint_Violation_Exception).appendMessage(e.getMessage());
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(ModelValidateException.class)
    public Result handleServiceException(ModelValidateException e) {
        logger.error("参数验证失败", e);
        StringBuilder sb = new StringBuilder();
        for (ValidateError ve : e.getGlobalErrors()) {
            sb.append(ve.getMessage() + ",");
        }
        for (Map.Entry<String, List<ValidateError>> m : e.getFieldErrors().entrySet()) {
            for (ValidateError ve : m.getValue()) {
                sb.append(ve.getMessage() + ",");
            }
        }
        return new DefaultResult().setResponseMessage(ResponseMessage.Model_Validate_Exception).appendMessage(sb.substring(0,sb.length()-1));
    }

    /**
     * 400 - Bad Request
     */
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Result handleValidationException(ValidationException e) {
        logger.error("参数验证失败", e);
        return new DefaultResult().setResponseMessage(ResponseMessage.Validation_Exception).appendMessage(e.getMessage());
    }

    /**
     * 405 - Method Not Allowed
     */
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        logger.error("不支持当前请求方法", e);
        return new DefaultResult().setResponseMessage(ResponseMessage.Http_Request_Method_Not_Supported_Exception).appendMessage(e.getMessage());
    }

    /**
     * 415 - Unsupported Media Type
     */
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(Exception e) {
        logger.error("不支持当前媒体类型", e);
        return new DefaultResult().setResponseMessage(ResponseMessage.Http_Media_Type_Not_Supported_Exception).appendMessage(e.getMessage());
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(BusinessException.class)
    public Result handleServiceException(BusinessException e) {
        logger.error("业务处理异常", e);
        return new DefaultResult().setResponseMessage(ResponseMessage.Business_Exception).appendMessage(e.getMessage());
    }

    /**
     * 500 - Internal Server Error
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        logger.error("普通异常", e);
        return new DefaultResult().setResponseMessage(ResponseMessage.Server_Exception).appendMessage(e.getMessage());
    }

    /**
     * 操作数据库出现异常:名称重复，外键关联
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(DataException.class)
    public Result handleException(DataException e) {
        logger.error("数据库操作异常:", e);
        return new DefaultResult().setResponseMessage(ResponseMessage.Data_Exception).appendMessage(e.getMessage());
    }

}
