package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import ru.fwoods.computerstore.model.DiscountProduct;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.service.PromotionProductService;

@RestController
public class PromotionProductRestController {

    @Autowired
    private PromotionProductService promotionProductService;

    @PostMapping(value = "/admib/promotion/product/create")
    public ResponseEntity createPromotionProduct(
            @RequestPart(name = "promotion") IdWrapper idWrapper,
            @RequestPart(name = "product") DiscountProduct discountProduct
    ) {
        promotionProductService.save(discountProduct, idWrapper);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admib/promotion/product/update")
    public ResponseEntity updatePromotionProduct(
            @RequestPart(name = "promotion") IdWrapper idWrapper,
            @RequestPart(name = "product") DiscountProduct discountProduct
    ) {
        promotionProductService.update(discountProduct, idWrapper);
        return ResponseEntity.ok().body(null);
    }
}
