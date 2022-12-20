package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 Controller
public class FirstApiController {
    @GetMapping("/api/hello")
    public String hello() {
        return "Hello World";
    }
}
