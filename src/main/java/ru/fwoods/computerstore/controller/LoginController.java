package ru.fwoods.computerstore.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.fwoods.computerstore.domain.User;

@Controller
public class LoginController {

    @GetMapping("/user/login")
    public String getLoginPage(@AuthenticationPrincipal User user) {
        if (user != null) {
            return "redirect:/admin";
        }
        return "site/authorization/login";
    }
}
