package com.maksimov.exceptions;

/**
 * Thrown to indicate that a method has been passed an error on service level.
 *
 * @author Vladislav Maksimov
 */
public class ServiceException extends Exception {

    /**
     * Constructs an <code>ServiceException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public ServiceException(String message) {
        super(message);
    }

}
