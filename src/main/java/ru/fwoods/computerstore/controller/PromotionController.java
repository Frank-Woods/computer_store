package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.fwoods.computerstore.model.Promotion;
import ru.fwoods.computerstore.service.PromotionService;

import java.util.Map;

@Controller
public class PromotionController {
    @Autowired
    private PromotionService promotionService;

    @GetMapping("/admin/promotions/create")
    public String getPromotionPage() {
        return "/promotion/create";
    }

    @GetMapping("/admin/promotions/update/{id}")
    public String getPromotionUpdatePage(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Promotion promotionModel = promotionService.getPromotionModelById(id);
        model.put("promotion", promotionModel);
        return "promotion/update";
    }
}
