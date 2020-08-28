package br.com.yfsmsystem.serviceone.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AppController {
    private Logger logger = LoggerFactory.getLogger(AppController.class);

    @GetMapping
    public ResponseEntity getResource() {
        logger.info("Hello app1");
        RestTemplate restTemplate = new RestTemplate();
        return ResponseEntity.ok().body(restTemplate.getForObject("http://localhost:9002/",String.class));
    }
    
}
