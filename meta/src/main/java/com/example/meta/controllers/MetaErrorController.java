package com.example.meta.controllers;

import com.example.meta.util.RequestHandler;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MetaErrorController implements ErrorController {

    @ExceptionHandler(Exception.class)
    private ResponseEntity handleException(Exception e) {
        return RequestHandler.getBadRequest(e);
    }

}

