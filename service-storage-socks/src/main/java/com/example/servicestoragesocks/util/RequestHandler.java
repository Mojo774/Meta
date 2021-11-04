package com.example.servicestoragesocks.util;

import org.springframework.http.ResponseEntity;

public class RequestHandler {
    public static ResponseEntity getBadRequest(Exception e) {
        return ResponseEntity.badRequest()
                .body(e.getMessage());
    }
}