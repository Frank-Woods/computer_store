package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.fwoods.computerstore.domain.Promotion;
import ru.fwoods.computerstore.service.ImageService;
import ru.fwoods.computerstore.service.PromotionService;

import java.util.Map;

@Controller
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/admin/promotion/create")
    public String getPromotionPage() {
        return "admin/promotion/create";
    }

    @GetMapping("/admin/promotion/update/{id}")
    public String getPromotionUpdatePage(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Promotion promotion = promotionService.findById(id);
        String banner = imageService.getImageByPromotionId(id);

        model.put("promotion", promotion);
        model.put("banner", banner);
        return "admin/promotion/update";
    }
}
