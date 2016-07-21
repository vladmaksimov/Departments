package com.maksimov.exceptions;

import java.util.Map;

/**
 * Created on 21.07.16.
 */
public class CustomValidateException extends Exception {

    private Map<String, String> errors;

    public CustomValidateException(String message, Map<String, String> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }
}
