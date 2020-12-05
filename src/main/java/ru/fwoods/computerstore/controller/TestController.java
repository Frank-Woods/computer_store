package ru.fwoods.computerstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("/cart")
    public String test() {
        return "site/cart/index";
    }
}
