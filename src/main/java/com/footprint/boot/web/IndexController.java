package com.footprint.boot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import reactor.core.publisher.Mono;

@Controller
public class IndexController {

    @RequestMapping("/index")
    public String home() {
        return "home";
    }

    @GetMapping("/hello")
    @ResponseBody
    public Mono<String> hello() {
        return Mono.just("Welcome to reactive world ~");
    }

}
