package com.kino.kino.controller;

import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {

    @GetMapping("/")
    public String index(Principal principal){
        if(principal != null) return "redirect:/app";
        return "index";
    }

    @GetMapping("/app")
    public String app(){
        return "app";
    }
}
