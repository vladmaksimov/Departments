package com.maksimov.models;

import java.util.List;
import java.util.Map;

/**
 * Created on 06.10.16.
 */
public class ValidateError {

    private Object object;
    private Map<String, List<String>> errors;

    public ValidateError(Object object, Map<String, List<String>> errors) {
        this.object = object;
        this.errors = errors;
    }


    public Object getObject() {
        return object;
    }

    public Map<String, List<String>> getErrors() {
        return errors;
    }
}
