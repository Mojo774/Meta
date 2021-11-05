package com.example.meta.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class GifService {

    @Autowired
    private RestTemplate restTemplate;

    private String serviceGifUrl = "http://service-gif";

    @HystrixCommand(fallbackMethod = "getFallbackGifLink")
    public String getGifLink() {
        String gifLink = restTemplate.getForObject(getRequest(), String.class);

        return gifLink;
    }

    private String getRequest() {
        String request = serviceGifUrl + "/link";

        return request;
    }

    private String getFallbackGifLink() {
        return "Something went wrong";
    }
}
