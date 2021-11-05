package com.example.meta.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GifService {

    @Autowired
    private RestTemplate restTemplate;

    private String serviceGifUrl = "http://service-gif";

    public String getGifLink(){
        String gifLink = restTemplate.getForObject(getRequest(), String.class);

        return gifLink;
    }

    private String getRequest(){
        String request = serviceGifUrl + "/link";

        return request;
    }

}
