package com.maksimov.controllers;

import com.maksimov.exceptions.DaoException;
import com.maksimov.exceptions.DispatcherException;
import com.maksimov.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * Created on 27.09.16.
 */
@ControllerAdvice
public class ErrorHandler {

    private static final String ERROR_SYSTEM_MESSAGE = "System error!";

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "error/problem";
    }

    @ExceptionHandler({ServiceException.class, DispatcherException.class})
    public String handleServiceException(Model model, ServiceException e) {
        return handleErrorPage(model, e.getMessage());
    }

    @ExceptionHandler(Error.class)
    public String handleError(Model model) {
        return handleErrorPage(model, ERROR_SYSTEM_MESSAGE);
    }

    private String handleErrorPage(Model model, String message) {
        model.addAttribute("error", message);
        return "error/error";
    }

}
