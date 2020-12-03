package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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

    @PostMapping(value = "/admin/promotion/{id}/product/create")
    public ResponseEntity createPromotionProduct(
            @PathVariable Long id,
            @RequestPart(name = "product") DiscountProduct discountProduct
    ) {
        promotionProductService.save(discountProduct, id);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/promotion/product/update")
    public ResponseEntity updatePromotionProduct(
            @RequestPart(name = "product") DiscountProduct discountProduct
    ) {
        promotionProductService.update(discountProduct);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/promotion/product/delete")
    public ResponseEntity updatePromotionProduct(
            @RequestPart(name = "product") IdWrapper idWrapper
    ) {
        promotionProductService.delete(ByIddiscountProduct);
        return ResponseEntity.ok().body(null);
    }
}
