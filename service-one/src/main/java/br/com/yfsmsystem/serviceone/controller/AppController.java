package br.com.yfsmsystem.serviceone.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping
    public ResponseEntity getResource() {
        return ResponseEntity.ok().body("My Service One");
    }
}
