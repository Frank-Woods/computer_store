package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fwoods.computerstore.domain.Promotion;
import ru.fwoods.computerstore.domain.PromotionProduct;
import ru.fwoods.computerstore.model.DiscountProduct;
import ru.fwoods.computerstore.model.ProductDataCart;
import ru.fwoods.computerstore.service.ImageService;
import ru.fwoods.computerstore.service.ProductDataService;
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

    @Autowired
    private ProductDataService productDataService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/promotion/create")
    public String getPromotionPage() {
        return "admin/promotion/create";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/promotion/{id}/product/all")
    public String getPromotionProductPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Promotion promotion = promotionService.findById(id);
        Pageable pageable = PageRequest.of(page, 15);
        Page<DiscountProduct> promotionProducts = promotionProductService.getAllByPromotionIdPage(id, pageable);

        model.put("promotion", promotion);
        model.put("products", promotionProducts);
        return "admin/promotion/product/all";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/promotion/{id}/product/create")
    public String getPromotionProductCreate(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Promotion promotion = promotionService.findById(id);

        model.put("promotion", promotion);
        return "admin/promotion/product/create";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/promotion/{promotionId}/product/update/{id}")
    public String getPromotionProductUpdate(
            @PathVariable Long promotionId,
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Promotion promotion = promotionService.findById(promotionId);
        DiscountProduct discountProduct = promotionProductService.getDiscountProductById(id);

        model.put("promotion", promotion);
        model.put("product", discountProduct);
        return "admin/promotion/product/update";
    }

    @GetMapping("/promotions")
    public String getPromotionsPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15, Sort.by("dateStart").descending());
        Page<Promotion> promotionsPage = promotionService.getPagePromotion(pageable);
        model.put("promotionsPage", promotionsPage);
        return "site/promotion/index";
    }

    @GetMapping("/promotions/{id}/products")
    public String getPromotionProducts(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Promotion promotion = promotionService.getPromotionById(id);
        Page<ProductDataCart> productDataCarts = productDataService.getProductDataCartsByPromotion(pageable, promotion.getId());

        model.put("promotion", promotion);
        model.put("productsPage", productDataCarts);

        return "site/promotion/product";
    }
}
