package br.com.yfsmsystem.servicetwo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {

    @GetMapping
    public ResponseEntity getResourceSystem(){
        return ResponseEntity.ok().body("My Second Service");
    }
}
