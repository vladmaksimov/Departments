package com.maksimov.controllers;

import com.maksimov.exceptions.DispatcherException;
import com.maksimov.exceptions.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.servlet.http.HttpServletResponse;

/**
 * @author Vladislav Maksimov
 */
@ControllerAdvice
public class ErrorHandler {

    private static final String ERROR_SYSTEM_MESSAGE = "System error!";

    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException() {
        return "error/problem";
    }

    @ResponseBody
    @ExceptionHandler({ServiceException.class, DispatcherException.class})
    public String handleServiceException(HttpServletResponse resp, ServiceException e) {
        resp.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return e.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(Error.class)
    public String handleError() {
        return ERROR_SYSTEM_MESSAGE;
    }

    private String handleErrorPage(Model model, String message) {
        model.addAttribute("error", message);
        return "error/error";
    }

}
