package com.example.meta.service;

import com.example.meta.dto.CatalogItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StorageService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    private String serviceStorageUrl = "http://service-storage-socks";

    public List<CatalogItem> getItems(
            String color,
            String operation,
            Integer cottonPart) throws HttpClientErrorException {

        List<CatalogItem> items = restTemplate.getForObject(
                getRequest(color, operation, cottonPart)
                , List.class);

        return items;
    }

    public ResponseEntity<String> income(
            String color,
            Integer cottonPart,
            Integer quantity) throws HttpClientErrorException {

        String str = getRequest(color, cottonPart, quantity, "income");

        ResponseEntity<String> response =
                restTemplate.exchange(str, HttpMethod.POST, HttpEntity.EMPTY, String.class);

        return response;
    }

    public ResponseEntity<String> outcome(
            String color,
            Integer cottonPart,
            Integer quantity) throws HttpClientErrorException {

        String str = getRequest(color, cottonPart, quantity, "outcome");

        ResponseEntity<String> response =
                restTemplate.exchange(str, HttpMethod.POST, HttpEntity.EMPTY, String.class);

        return response;
    }

    private String getRequest(String color, Integer cottonPart, Integer quantity, String method) {
        String request = String.format(
                serviceStorageUrl + "/storage/" + method + "?color=%s&cottonPart=%d&quantity=%d",
                color, cottonPart, quantity);

        return request;
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
}
