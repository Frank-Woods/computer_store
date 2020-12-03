package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
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
}
