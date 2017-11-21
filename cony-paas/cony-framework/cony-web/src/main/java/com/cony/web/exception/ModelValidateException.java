package com.cony.web.exception;


import com.cony.data.common.validate.ValidateError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by luorh on 2017/1/6.
 */
public class ModelValidateException extends RuntimeException {

    private List<ValidateError> globalErrors = new ArrayList<ValidateError>();
    private Map<String, List<ValidateError>> fieldErrors = new HashMap<String, List<ValidateError>>();

    public ModelValidateException(String message) {
        super(message);
    }

    public void addGlobalError(ValidateError validateError) {
        this.globalErrors.add(validateError);
    }

    public void addFieldErrors(String field, ValidateError validateError) {
        List<ValidateError> errors = fieldErrors.get(field);
        if (errors == null) {
            errors = new ArrayList<ValidateError>();
            fieldErrors.put(field, errors);
        }
        errors.add(validateError);
    }

    public List<ValidateError> getGlobalErrors() {
        return globalErrors;
    }

    public Map<String, List<ValidateError>> getFieldErrors() {
        return fieldErrors;
    }

}
