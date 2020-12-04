package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fwoods.computerstore.domain.*;
import ru.fwoods.computerstore.service.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
public class ProductDataController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ProductDataService productDataService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private AttributeCategoryService attributeCategoryService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/admin/product/create")
    public String getProductsCreate(Map<String, Object> model) {

        List<Country> countries = countryService.getAllCountries();
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();

        model.put("countries", countries);
        model.put("manufacturers", manufacturers);

        return "admin/product/create";
    }

    @GetMapping("/admin/product/update/{id}")
    public String getProductsUpdate(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        ProductData productData = productDataService.getProductDataById(id);
        List<Country> countries = countryService.getAllCountries();
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        List<Image> images = imageService.getImagesByProductDataId(id);

        model.put("productData", productData);
        model.put("countries", countries);
        model.put("manufacturers", manufacturers);
        model.put("images", images);

        return "admin/product/update";
    }

    @GetMapping("/store/product/{id}")
    public String getProductPage(
            @AuthenticationPrincipal User user,
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        ProductData productData = productDataService.getProductDataById(id);
        List<AttributeCategory> attributeCategories = attributeCategoryService.getAttributeCategoriesByProductDataId(productData.getId());
        Integer discountCost = productDataService.getDiscountCost(id);
        Double rating = productDataService.getRating(id);
        Review review = null;
        if (user != null) review = reviewService.getReviewByUserAndProductData(user.getId(), productData.getId());

        model.put("productData", productData);
        model.put("attributeCategories", attributeCategories);
        model.put("discountCost", discountCost);
        model.put("rating", rating);
        model.put("review", review);

        return "site/store/product/index";
    }
}
