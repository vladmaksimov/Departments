package com.maksimov.exceptions;

/**
 * Thrown to indicate that a method has been passed an database error.
 *
 * @author  Vladyslav Maksymov
 */
public class DaoException extends Exception {

    /**
     * Constructs an <code>DaoException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public DaoException(String message) {
        super(message);
    }
}
