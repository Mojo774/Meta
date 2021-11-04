package com.example.servicegif.service;

import com.example.servicegif.clients.RateClient;
import com.example.servicegif.models.TypeGif;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;


@Service
public class RateService {

    @Autowired
    private RateClient rateClient;

    @Value("${service.rate.app_id}")
    private String app_id;

    @Value("${service.rate.base}")
    private String base;

    @Value("${service.rate.currency}")
    private String currency;

    public TypeGif getTypeGif() {
        LocalDate date = LocalDate.now().minusDays(1);

        return TypeGif.getType(getRate().compareTo(getRate(date)));
    }

    public Double getRate() {
        String json = rateClient.findRate(app_id, base);

        JSONObject request = new JSONObject(json);

        Double rate = request.getJSONObject("rates").getDouble(currency);

        return rate;
    }

    public Double getRate(LocalDate date) {

        String json = rateClient.findRateData(date.toString(), app_id, base);

        JSONObject request = new JSONObject(json);

        Double rate = request.getJSONObject("rates").getDouble(currency);

        return rate;
    }
}
