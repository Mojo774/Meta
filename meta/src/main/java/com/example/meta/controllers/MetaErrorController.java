package com.example.meta.controllers;

import com.example.meta.util.RequestHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MetaErrorController implements ErrorController {

    /*@RequestMapping("/error")
    public String handleError() {
        //do something like logging
        return "error";
    }*/

    @ExceptionHandler(Exception.class)
    private ResponseEntity handleException(Exception e) {
        return RequestHandler.getBadRequest(e);
    }
}

