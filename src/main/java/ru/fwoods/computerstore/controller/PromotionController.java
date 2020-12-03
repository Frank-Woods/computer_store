package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.fwoods.computerstore.domain.Promotion;
import ru.fwoods.computerstore.domain.PromotionProduct;
import ru.fwoods.computerstore.model.DiscountProduct;
import ru.fwoods.computerstore.service.ImageService;
import ru.fwoods.computerstore.service.PromotionProductService;
import ru.fwoods.computerstore.service.PromotionService;

import java.util.List;
import java.util.Map;

@Controller
public class PromotionController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private PromotionProductService promotionProductService;

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

        model.put("promotion", promotion);
        model.put("banner", promotion.getBanner().getFilename());
        return "admin/promotion/update";
    }

    @GetMapping("/admin/promotion/{id}/product/all")
    public String getPromotionProductPage(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        List<DiscountProduct> promotionProducts = promotionProductService.getAllByPromotionId(id);

        model.put("products", promotionProducts);
        return "admin/promotion/product/all";
    }

    @GetMapping("/admin/promotion/product/create")
    public String getPromotionProductCreate() {
        return "admin/promotion/product/create";
    }

    @GetMapping("/admin/promotion/product/update/{id}")
    public String getPromotionProductUpdate(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        DiscountProduct discountProduct = promotionProductService.getDiscountProductById(id);

        model.put("product", discountProduct);
        return "admin/promotion/product/update";
    }
}
