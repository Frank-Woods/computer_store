package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fwoods.computerstore.domain.ProductCategory;
import ru.fwoods.computerstore.domain.ProductData;
import ru.fwoods.computerstore.domain.Promotion;
import ru.fwoods.computerstore.model.Attribute;
import ru.fwoods.computerstore.model.DiscountProduct;
import ru.fwoods.computerstore.service.AttributeService;
import ru.fwoods.computerstore.service.ProductDataService;

import java.util.Map;

@Controller
public class AttributeController {

    @Autowired
    private ProductDataService productDataService;

    @Autowired
    private AttributeService attributeService;

    @GetMapping("/admin/product/{id}/attribute/all")
    public String getAttributePage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        ProductData productData = productDataService.getProductDataById(id);
        Pageable pageable = PageRequest.of(page, 15);
        Page<Attribute> attributes = attributeService.getAllAttributesByProductId(id, pageable);

        model.put("productData", productData);
        model.put("attributes", attributes);
        return "admin/product/attribute/all";
    }

    @GetMapping("/admin/product/{id}/attribute/create")
    public String getAttributeCreate(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        ProductData productData = productDataService.getProductDataById(id);

        model.put("productData", productData);
        return "admin/product/attribute/create";
    }

    @GetMapping("/admin/product/{productId}/attribute/update/{id}")
    public String getAttributeUpdate(
            @PathVariable Long productId,
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        ProductData productData = productDataService.getProductDataById(productId);
        Attribute attribute = attributeService.getAttributeValueModelById(id);

        model.put("productData", productData);
        model.put("attribute", attribute);
        return "admin/category/attribute/update";
    }
}
