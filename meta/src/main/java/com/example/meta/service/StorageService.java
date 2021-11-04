package com.example.meta.service;

import com.example.meta.dto.CatalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StorageService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    private String serviceStorageUrl = "http://service-storage-socks";

    public List<CatalogItem> getItems(String color, String operation, Integer cottonPart) {

        List<CatalogItem> items = restTemplate.getForObject(
                getRequest(color, operation, cottonPart)
                , List.class);
        return items;
    }

    private String getRequest(String color, String operation, Integer cottonPart) {
        String request = String.format(serviceStorageUrl + "/storage?color=%s", color);

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

    public ResponseEntity<String> income(String color, Integer cottonPart, Integer quantity) {
        String str = String.format(serviceStorageUrl + "/storage/income?color=%s&cottonPart=%d&quantity=%d",
                color, cottonPart, quantity);

        ResponseEntity<String> response =
                restTemplate.exchange(str, HttpMethod.POST, HttpEntity.EMPTY, String.class);

        return response;
    }

    public ResponseEntity<String> outcome(String color, Integer cottonPart, Integer quantity) {
        String str = String.format(serviceStorageUrl + "/storage/outcome?color=%s&cottonPart=%d&quantity=%d",
                color, cottonPart, quantity);

        ResponseEntity<String> response =
                restTemplate.exchange(str, HttpMethod.POST, HttpEntity.EMPTY, String.class);

        return response;
    }
}
