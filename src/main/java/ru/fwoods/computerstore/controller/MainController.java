package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fwoods.computerstore.domain.Manufacturer;
import ru.fwoods.computerstore.domain.Promotion;
import ru.fwoods.computerstore.model.ProductDataCart;
import ru.fwoods.computerstore.service.EmailService;
import ru.fwoods.computerstore.service.ManufacturerService;
import ru.fwoods.computerstore.service.ProductDataService;
import ru.fwoods.computerstore.service.PromotionService;

import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    @Autowired
    private ProductDataService productDataService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public String getMainPage(Map<String, Object> model) {
        Promotion promotion = promotionService.findActivePromotion();
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        List<ProductDataCart> productDataCarts = productDataService.getPopularProducts();
        model.put("promotion", promotion);
        model.put("manufacturers", manufacturers);
        model.put("products", productDataCarts);
        return "main/index";
    }

    @PostMapping("/add/email")
    public String addEmail(
            @RequestParam(name = "email") String email
    ) {
        emailService.saveEmail(email);
        return "redirect:/";
    }
}
