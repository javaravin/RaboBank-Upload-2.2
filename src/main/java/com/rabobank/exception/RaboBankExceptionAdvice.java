package com.rabobank.exception;

import com.rabobank.util.ResponseBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * This class will do handle the exceptions.
 *
 * @author Ravi Naganaboyina
 */
@ControllerAdvice
@Primary
public class RaboBankExceptionAdvice {

    private ResponseBuilder responseBuilder;

    private static final String STATUS="status";
    private static final String MESSAGE="message";

    @Autowired
    public RaboBankExceptionAdvice(ResponseBuilder responseBuilder) {
        this.responseBuilder = responseBuilder;
    }

    @ExceptionHandler(RaboBankException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ModelAndView processInternalFailed(RaboBankException raboBankException) {
        return new ModelAndView(STATUS, MESSAGE, responseBuilder.buildFailureResponse(raboBankException.getMessageKey()));
    }

    @ExceptionHandler(FileProcessException.class)
    @org.springframework.web.bind.annotation.ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ModelAndView fileProcessFailed(FileProcessException raboBankException) {
        return new ModelAndView(STATUS, MESSAGE, responseBuilder.buildFailureResponse(raboBankException.getMessageKey()));
    }
}
