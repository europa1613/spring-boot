package com.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @GetMapping("/ping")
    public String pong() throws JsonProcessingException {
        Map<String, String> res = new HashMap<>();
        res.put("ping", "pong");
        return new ObjectMapper().writeValueAsString(res);
    }
}
