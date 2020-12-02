package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fwoods.computerstore.domain.Manufacturer;
import ru.fwoods.computerstore.domain.ProductCategory;
import ru.fwoods.computerstore.service.ManufacturerService;
import ru.fwoods.computerstore.service.ProductCategoryService;

import java.util.Map;

@Controller
public class ProductDataController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ManufacturerService manufacturerService;

//    @GetMapping("/admin/products/create")
//    public String getProductsCreate(
//            @RequestParam(required = false, defaultValue = "0") Integer page,
//            Map<String, Object> model
//    ) {
//        Pageable pageable = PageRequest.of(page, 15);
//        Page<ProductCategory> categoriesPage = productCategoryService.getCategoriesPagesWithoutParent(pageable);
//        Page<Manufacturer> manufacturersPage = manufacturerService.getPageManufacturers(pageable);
//
//        model.put("categoriesPage", categoriesPage);
//        model.put("manufacturersPage", manufacturersPage);
//
//        return "product/create";
//    }
}
