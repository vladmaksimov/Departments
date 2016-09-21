package com.maksimov.exceptions;

/**
 * Thrown to indicate that a method has been passed an database error.
 *
 * @author  Vladyslav Maksymov
 */
public class DepartmentException extends Exception {

    /**
     * Constructs an <code>DepartmentException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public DepartmentException(String message) {
        super(message);
    }
}
