package com.sosbicho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BichoController {

    @GetMapping("/")
    public String home() {
        return "home";
    }

}
