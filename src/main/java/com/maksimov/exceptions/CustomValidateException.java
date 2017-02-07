package com.maksimov.exceptions;

import com.maksimov.models.ValidateError;

import java.util.List;

/**
 * Thrown to indicate that a method has been passed an error on oval object validation.
 *
 * @author Vladislav Maksimov
 */
public class CustomValidateException extends Exception {

    private ValidateError error;

    /**
     * Constructs an <code>CustomValidateException</code> with the
     * specified detail message and error map.
     *
     * @param message the detail message.
     * @param error   the {@link ValidateError} object with errors, where key is object @{@link String} field,
     *                and value is {@link List} of {@link String} values with error messages and object of validation.
     */
    public CustomValidateException(String message, ValidateError error) {
        super(message);
        this.error = error;
    }

    public ValidateError getError() {
        return error;
    }

}
