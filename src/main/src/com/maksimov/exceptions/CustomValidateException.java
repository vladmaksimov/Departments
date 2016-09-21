package com.maksimov.exceptions;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Thrown to indicate that a method has been passed an error on oval object validation.
 *
 * @author Vladyslav Maksymov
 */
public class CustomValidateException extends Exception {

    private Map<String, List<String>> errors;

    /**
     * Constructs an <code>CustomValidateException</code> with the
     * specified detail message and error map.
     *
     * @param message the detail message.
     * @param errors  the {@link Map} object with errors, where key is object @{@link String} field,
     *                and value is {@link List} of {@link String} values with error messages.
     */
    public CustomValidateException(String message, Map<String, List<String>> errors) {
        super(message);
        this.errors = errors;
    }

    public Map<String, List<String>> getErrors() {
        return Collections.unmodifiableMap(errors);
    }

}
