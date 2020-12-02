package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.fwoods.computerstore.model.User;
import ru.fwoods.computerstore.service.RegistrationService;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @GetMapping("user/registration")
    public String getRegistrationPage() {
        return "user/registration";
    }

    @PostMapping("user/registration")
    public String saveUser(User user){
        registrationService.save(user);
        return "redirect:/admin";
    }
}
