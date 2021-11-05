package com.example.servicegif.controllers;

import com.example.servicegif.models.TypeGif;
import com.example.servicegif.service.GifService;
import com.example.servicegif.service.RateService;
import com.example.servicegif.util.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping()
public class GifController {

    @Autowired
    private RateService rateService;

    @Autowired
    private GifService gifService;

    @GetMapping()
    public ResponseEntity getGif() {

        TypeGif typeGif = rateService.getTypeGif();
        String response = gifService.getGifLink(typeGif);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create(response));

        return new ResponseEntity(headers, HttpStatus.SEE_OTHER);
    }

    @GetMapping("/link")
    public String getGifLink() {
        TypeGif typeGif = rateService.getTypeGif();
        String response = gifService.getGifLink(typeGif);

        return response;
    }

    @ExceptionHandler(Exception.class)
    private ResponseEntity handleException(Exception e) {
        return RequestHandler.getBadRequest(e);
    }

}