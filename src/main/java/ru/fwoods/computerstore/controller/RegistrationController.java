package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.fwoods.computerstore.domain.City;
import ru.fwoods.computerstore.model.User;
import ru.fwoods.computerstore.service.CityService;
import ru.fwoods.computerstore.service.RegistrationService;

import java.util.List;
import java.util.Map;

@Controller
public class RegistrationController {
    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private CityService cityService;

    @GetMapping("/user/registration")
    public String getRegistrationPage(Map<String, Object> model){
        List<City> cities = cityService.getAllCities();
        model.put("cities", cities);
        return "site/authorization/registration";
    }
}
