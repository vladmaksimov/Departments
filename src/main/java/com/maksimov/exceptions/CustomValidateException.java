package com.maksimov.exceptions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created on 21.07.16.
 */
public class CustomValidateException extends Exception {

    private Map<String, List<String>> errors;

    public CustomValidateException(String message, Map<String, List<String>> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, List<String>> getErrors() {
        return Collections.unmodifiableMap(errors);
    }

}
