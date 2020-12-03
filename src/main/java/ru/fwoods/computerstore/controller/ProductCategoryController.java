package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fwoods.computerstore.domain.ProductCategory;
import ru.fwoods.computerstore.domain.Promotion;
import ru.fwoods.computerstore.model.DiscountProduct;
import ru.fwoods.computerstore.model.rest.Attribute;
import ru.fwoods.computerstore.service.AttributeService;
import ru.fwoods.computerstore.service.ProductCategoryService;

import java.util.Map;

@Controller
public class ProductCategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private AttributeService attributeService;

    @GetMapping("/admin/category/create")
    public String getProductCategoryCreate() {
        return "admin/category/create";
    }

    @GetMapping("/admin/productCategories/update/{id}")
    public String getProductCategoryUpdate(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        ProductCategory productCategory = productCategoryService.getCategoryById(id);
        model.put("category", productCategory);
        return "admin/category/update";
    }

    @GetMapping("/admin/category/{id}/attribute/all")
    public String getPromotionProductPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        ProductCategory productCategory = productCategoryService.getCategoryById(id);
        Pageable pageable = PageRequest.of(page, 15);
        Page<Attribute> attributes = attributeService.getAllByProductCategoryId(productCategory, pageable);

        model.put("category", productCategory);
        model.put("attributes", attributes);
        return "admin/category/attribute/all";
    }

    @GetMapping("/admin/category/{id}/attribute/create")
    public String getPromotionProductCreate(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        ProductCategory productCategory = productCategoryService.getCategoryById(id);

        model.put("category", productCategory);
        return "admin/category/attribute/create";
    }

    @GetMapping("/admin/category/attribute/update/{id}")
    public String getPromotionProductUpdate(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Attribute attribute = attributeService.getAttributeModelById(id);

        model.put("attribute", attribute);
        return "admin/category/attribute/update";
    }
}
