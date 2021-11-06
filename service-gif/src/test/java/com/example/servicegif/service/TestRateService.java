package com.example.servicegif.service;

import com.example.servicegif.clients.RateClient;
import com.example.servicegif.models.TypeGif;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class TestRateService {

    @MockBean
    private RateService rateService;

    @MockBean
    private RateClient rateClient;

    @Value("${service.rate.app_id}")
    private String app_id;

    @Value("${service.rate.base}")
    private String base;

    @Value("${service.rate.currency}")
    private String currency;

    private final Double more = 10.0;
    private final Double less = 1.0;

    @Test
    void getRate() {

        given(rateClient.findRate(app_id, base)).willReturn(
                "{\"rates\":{\"" + currency + "\":\"3.2\"}}"
        );

        String json = rateClient.findRate(app_id, base);
        JSONObject request = new JSONObject(json);
        Double rate = request.getJSONObject("rates").getDouble(currency);

        Assertions.assertNotNull(rate);
    }

    @Test
    void getTypeGifRich() {

        LocalDate date = LocalDate.now().minusDays(1);

        given(rateService.getRate()).willReturn(more);
        given(rateService.getRate(date)).willReturn(less);

        /*Mockito.doReturn("{\"rates\":{\"" + currency + "\":" + anyDouble() + "}}")
                .when(rateClient)
                .findRate(app_id);*/

        TypeGif typeGif = TypeGif.getType(rateService.getRate().compareTo(rateService.getRate(date)));

        //Mockito.verify(rateClient, Mockito.times(1)).findRate(app_id);
        Assertions.assertEquals(TypeGif.rich, typeGif);
    }

    @Test
    void getTypeGifBroke() {

        LocalDate date = LocalDate.now().minusDays(1);

        given(rateService.getRate()).willReturn(less);
        given(rateService.getRate(date)).willReturn(more);

        TypeGif typeGif = TypeGif.getType(rateService.getRate().compareTo(rateService.getRate(date)));

        Assertions.assertEquals(TypeGif.broke, typeGif);
    }

}
