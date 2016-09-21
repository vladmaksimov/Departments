package com.maksimov.exceptions;

/**
 * Thrown to indicate that a method has been passed an error on servlet level.
 *
 * @author  Vladyslav Maksymov
 */
public class DispatcherException extends Exception {

    /**
     * Constructs an <code>DispatcherException</code> with the
     * specified detail message.
     *
     * @param message the detail message.
     */
    public DispatcherException(String message) {
        super(message);
    }

}
