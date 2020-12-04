package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.fwoods.computerstore.domain.ProductCategory;
import ru.fwoods.computerstore.service.AttributeCategoryService;
import ru.fwoods.computerstore.service.ProductCategoryService;

import java.util.List;
import java.util.Map;

@Controller
public class CategoryController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/site/catalog")
    public String getCatalogPage(Map<String, Object> model) {
        List<ProductCategory> productCategories = productCategoryService.getCategoriesWithoutParent();
        model.put("categories", productCategories);
        return "site/catalog/index";
    }

    @GetMapping("/site/catalog/{id}")
    public String getCatalogPage(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        ProductCategory productCategory = productCategoryService.getCategoryById(id);
        List<ProductCategory> productCategories = productCategoryService.getChildCategories(id);

        if (productCategories.isEmpty()) {
            return "redirect:/store/index";
        }

        model.put("category", productCategory);
        model.put("categories", productCategories);
        return "site/catalog/index";
    }
}
