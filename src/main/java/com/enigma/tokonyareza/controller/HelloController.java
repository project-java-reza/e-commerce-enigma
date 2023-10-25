package com.enigma.tokonyareza.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/hello")
    public String hello() {
        return "<h1>hello world</h1>";
    }

    @GetMapping(value = "/hello2")
    public String hello2() {
        return "<h1>hello world2</h1>";
    }

    @GetMapping(value = "/hello/v1")
    public String[] getHobies() {
        return new String[] {"tidur", "makan"};
    }

    @GetMapping("/request-param{key}")
    public String getRequestParam(@RequestParam String key) {
        return key;
    }

    @GetMapping("/data/{id}")
    public String getDataById(@PathVariable String id) {
        return "Data " + id;
    }

}
