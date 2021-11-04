package com.example.servicegif.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-gif", url = "${service.gif.url}")
public interface GifClient {

    @GetMapping("/v1/gifs/random")
    String findGif(@RequestParam("api_key") String api_key, @RequestParam("tag") String tag);

}