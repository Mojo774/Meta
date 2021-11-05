package com.example.meta.controllers;

import com.example.meta.service.GifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/gif")
public class GifController {

    @Autowired
    private GifService gifService;

    @GetMapping("/get")
    public ResponseEntity getGif() {
        String response = gifService.getGifLink();

        return new ResponseEntity(response, HttpStatus.OK);
    }
}
