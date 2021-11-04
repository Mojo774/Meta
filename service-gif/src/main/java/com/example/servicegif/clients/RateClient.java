package com.example.servicegif.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "client-rate", url = "${service.rate.url}")
public interface RateClient {

    @GetMapping("/latest.json")
    String findRate(@RequestParam("app_id") String app_id,
                    @RequestParam("base") String base);

    @GetMapping("/historical/{data}.json")
    String findRateData(
            @RequestParam("data") String data,
            @RequestParam("app_id") String app_id,
            @RequestParam("base") String base);
}
