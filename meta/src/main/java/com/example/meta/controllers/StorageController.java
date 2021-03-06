package com.example.meta.controllers;

import com.example.meta.dto.CatalogItem;
import com.example.meta.service.StorageService;
import com.example.meta.util.RequestHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/catalog")
public class StorageController {

    @Autowired
    private StorageService storageService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<CatalogItem>> getSocks(
            @RequestParam(required = false, defaultValue = "") String color,
            @RequestParam(required = false) String operation,
            @RequestParam(required = false) Integer cottonPart) {

        List<CatalogItem> items = storageService.getItems(color, operation, cottonPart);

        return new ResponseEntity(
                items,
                HttpStatus.OK);
    }

    @PostMapping("/income")
    public ResponseEntity<String> incomeSocks(
            @RequestParam String color,
            @RequestParam Integer cottonPart,
            @RequestParam Integer quantity) {

        return storageService.income(color, cottonPart, quantity);
    }

    @PostMapping("/outcome")
    public ResponseEntity<String> outcomeSocks(
            @RequestParam String color,
            @RequestParam Integer cottonPart,
            @RequestParam Integer quantity) {

        return storageService.outcome(color, cottonPart, quantity);
    }

}
