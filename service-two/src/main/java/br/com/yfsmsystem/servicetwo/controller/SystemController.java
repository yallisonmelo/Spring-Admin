package br.com.yfsmsystem.servicetwo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SystemController {
    private Logger logger = LoggerFactory.getLogger(SystemController.class);
    @GetMapping
    public ResponseEntity getResourceSystem(){

        logger.info("Hello app2");
        return ResponseEntity.ok().body("My Second Service");
    }
}
