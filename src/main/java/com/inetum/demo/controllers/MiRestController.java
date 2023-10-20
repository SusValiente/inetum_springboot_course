package com.inetum.demo.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MiRestController {


    @GetMapping("/")
    public String welcome() {
        return "Bienvenido a Spring Boot!!!";
    }

}
