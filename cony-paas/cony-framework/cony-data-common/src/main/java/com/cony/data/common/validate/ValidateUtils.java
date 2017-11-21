package com.cony.data.common.validate;


import com.cony.data.common.exception.ModelValidateException;

import javax.validation.*;
import javax.validation.metadata.ConstraintDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 */
public class ValidateUtils {
    private static final String DEFAULT_ERROR_MESSAGE = "数据不满足约束";

    private static Validator validator;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     * 规则校验集合   Created by zhul-a on 2017/4/17.
     *
     * @param list
     * @param <T>
     */
    public static <T> void valid(List<T> list) {
        Set<ConstraintViolation<T>> violations = new HashSet<ConstraintViolation<T>>();
        for (T entity : list) {
            violations.addAll(validator.validate(entity));
        }
        checkConstraintViolations(DEFAULT_ERROR_MESSAGE, violations);
    }

    public static <T> void valid(T model) {
        valid(model, DEFAULT_ERROR_MESSAGE);
    }

    public static <T> void valid(T model, String message) {
        Set<ConstraintViolation<T>> violations = validator.validate(model);
        checkConstraintViolations(message, violations);
    }

    public static <T> void valid(T model, Class<?>[] groups) {
        valid(model, groups, DEFAULT_ERROR_MESSAGE);
    }

    public static <T> void valid(T model, Class<?>[] groups, String message) {
        Set<ConstraintViolation<T>> violations = validator.validate(model, groups);
        checkConstraintViolations(message, violations);
    }

    public static <T> void validProp(T model, String propName) {
        validProp(model, propName, DEFAULT_ERROR_MESSAGE);
    }

    public static <T> void validProp(T model, String propName, String message) {
        Set<ConstraintViolation<T>> violations = validator.validateProperty(model, propName);
        checkConstraintViolations(message, violations);
    }

    public static <T> void validProp(T model, String propName, Class<?>[] groups) {
        validProp(model, propName, groups, DEFAULT_ERROR_MESSAGE);
    }


    public static <T> void validProp(T model, String propName, Class<?>[] groups, String message) {
        Set<ConstraintViolation<T>> violations = validator.validateProperty(model, propName, groups);
        checkConstraintViolations(message, violations);
    }

    public static <T> void validValue(Class<T> modelClass, String propName, Object value) {
        validValue(modelClass, propName, value, DEFAULT_ERROR_MESSAGE);
    }

    public static <T> void validValues(Class<T> modelClass, List<Map<String, Object>> list) {

        validValues(modelClass, list, DEFAULT_ERROR_MESSAGE);
    }

    public static <T> void validValue(Class<T> modelClass, String propName, Object value, String message) {
        Set<ConstraintViolation<T>> violations = validator.validateValue(modelClass, propName, value);
        checkConstraintViolations(message, violations);
    }

    public static <T> void validValues(Class<T> modelClass, List<Map<String, Object>> list, String message) {
        ModelValidateException modelValidateException = new ModelValidateException(message);
        Boolean passValid = true;
        if (list.size() > 0) {
            for (Map<String, Object> keyValue : list) {
                for (Map.Entry<String, Object> entry : keyValue.entrySet()) {
                    Set<ConstraintViolation<T>> violations = validator.validateValue(modelClass, entry.getKey(), entry.getValue());
                    if (violations.size() > 0) {
                        passValid = false;
                        bindViolationAndException(modelValidateException, violations);
                    }
                }
            }
            if (!passValid) {
                throw modelValidateException;
            }
        }
    }

    public static <T> void validValues(Class<T> modelClass, Map<String, Object> map) {
        ModelValidateException modelValidateException = new ModelValidateException(DEFAULT_ERROR_MESSAGE);
        Boolean passValid = true;
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Set<ConstraintViolation<T>> violations = validator.validateValue(modelClass, entry.getKey(), entry.getValue());
            if (violations.size() > 0) {
                passValid = false;
                bindViolationAndException(modelValidateException, violations);
            }
        }
        if (!passValid) {
            throw modelValidateException;
        }
    }

    public static <T> void validValue(Class<T> modelClass, String propName, Object value, Class<?>[] groups, String message) {
        Set<ConstraintViolation<T>> violations = validator.validateValue(modelClass, propName, value, groups);
        checkConstraintViolations(message, violations);
    }

    private static <T> void checkConstraintViolations(String message, Set<ConstraintViolation<T>> violations) {
        if (violations.size() > 0) {
            throw buildModelValidateException(message, violations);
        }
    }

    private static <T> ModelValidateException buildModelValidateException(
            String message, Set<ConstraintViolation<T>> violations) {
        ModelValidateException result = new ModelValidateException(message);
        bindViolationAndException(result, violations);
        return result;
    }

    private static <T> void bindViolationAndException(ModelValidateException modelValidateException, Set<ConstraintViolation<T>> violations) {
        for (ConstraintViolation<T> violation : violations) {
            ConstraintDescriptor<?> descriptor = violation.getConstraintDescriptor();
            String errorCode = descriptor.getAnnotation().annotationType().getSimpleName();
            String errorMessage = violation.getMessage();
            ValidateError validateError = new ValidateError(errorCode, errorMessage);
            Path propertyPath = violation.getPropertyPath();
            if (propertyPath == null) {
                modelValidateException.addGlobalError(validateError);
            } else {
                modelValidateException.addFieldErrors(propertyPath.toString(), validateError);
            }
        }


    }
}
