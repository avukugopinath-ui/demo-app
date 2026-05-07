package com.example.demo;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/demo")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from Demo Microservice!";
    }
}