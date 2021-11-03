package com.example.meta.service;

import com.example.meta.dto.CatalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StorageService {

    @Autowired
    private RestTemplate restTemplate;

    public List<CatalogItem> getItems(String color, String operation, Integer cottonPart) {

        List<CatalogItem> items = restTemplate.getForObject(
                getRequest(color, operation, cottonPart)
                , List.class);
        return items;
    }

    private String getRequest(String color, String operation, Integer cottonPart) {
        String request = String.format("http://localhost:8081/storage?color=%s", color);

        if (operation == null && cottonPart == null) {
            return request;
        }

        if (operation != null && cottonPart != null) {
            return String.format("%s%s",
                    request,
                    String.format("&operation=%s&cottonPart=%s", operation, cottonPart));
        }


        if (operation != null) {
            return String.format("%s%s",
                    request,
                    String.format("&operation=%s", operation));
        } else {
            return String.format("%s%s",
                    request,
                    String.format("&cottonPart=%s", cottonPart));
        }
    }
}
