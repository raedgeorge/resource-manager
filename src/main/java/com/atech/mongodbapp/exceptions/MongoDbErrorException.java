package com.atech.mongodbapp.exceptions;

import com.mongodb.MongoTimeoutException;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class MongoDbErrorException {

    @ExceptionHandler({DataAccessResourceFailureException.class, MongoTimeoutException.class})
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    public ModelAndView handleException(Exception exception){

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("dberror");
        modelAndView.addObject("exception", exception);

        return modelAndView;
    }
}
