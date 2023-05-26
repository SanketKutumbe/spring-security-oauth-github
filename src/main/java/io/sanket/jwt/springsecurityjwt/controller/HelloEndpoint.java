package io.sanket.jwt.springsecurityjwt.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloEndpoint {

    @RequestMapping("/hi")
    public String hello(){
        return "Hello";
    }
}
