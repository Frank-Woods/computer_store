package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.fwoods.computerstore.domain.ProductCategory;
import ru.fwoods.computerstore.service.ProductCategoryService;

@Controller
public class ProductCategoryController {
    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping("/admin/productCategories/create")
    public String getProductCategoryCreate() {
        return "productCategory/create";
    }

    @GetMapping("/admin/productCategories/update/{id}")
    public String getProductCategoryUpdate(
            @PathVariable Long id,
            Model model
    ) {
        ProductCategory productCategory = productCategoryService.getCategoryById(id);
        model.addAttribute("productCategory", productCategory);
        return "productCategory/update";
    }

    @PostMapping("/admin/productCategories/delete")
    public String deleteProductCategory(Long id) {
        productCategoryService.deleteCategoryById(id);
        return "redirect:/admin/productCategory";
    }
}
