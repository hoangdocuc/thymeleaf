package com.hoangdocuc.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @RestController
    public class HomeAPI {

        @GetMapping("/api/test")
        public ResponseEntity<String> testSpringBoot() {
            return ResponseEntity.ok("Success");
        }


    }

}
