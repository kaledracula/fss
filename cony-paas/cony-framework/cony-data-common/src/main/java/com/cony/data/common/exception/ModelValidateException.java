package com.cony.data.common.exception;


import com.cony.data.common.validate.ValidateError;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class ModelValidateException extends DataConstraintException {
    private List<ValidateError> globalErrors = new ArrayList<>();
    private Map<String, List<ValidateError>> fieldErrors = new HashMap<>();
    private Log log = LogFactory.getLog(this.getClass());

    public ModelValidateException(String message) {
        super(message);
    }

    public void addGlobalError(ValidateError validateError) {
        this.globalErrors.add(validateError);
    }

    public void addFieldErrors(String field, ValidateError validateError) {
        log.error(validateError.getMessage());
        List<ValidateError> errors = fieldErrors.get(field);
        if (errors == null) {
            errors = new ArrayList<>();
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
