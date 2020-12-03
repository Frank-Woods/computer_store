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
import ru.fwoods.computerstore.domain.Country;
import ru.fwoods.computerstore.domain.Image;
import ru.fwoods.computerstore.domain.Manufacturer;
import ru.fwoods.computerstore.domain.ProductCategory;
import ru.fwoods.computerstore.model.ProductData;
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
        ProductData productDataModel = productDataService.getProductDataModelById(id);
        List<Country> countries = countryService.getAllCountries();
        List<Manufacturer> manufacturers = manufacturerService.getAllManufacturers();
        Country country = countryService.getCountryById(productDataModel.getCountry());
        Manufacturer manufacturer = manufacturerService.getManufacturerById(productDataModel.getManufacturer());
        ProductCategory productCategory = productCategoryService.getCategoryById(productDataModel.getCategory());
        List<Image> images = imageService.getImagesByProductDataId(productDataModel.getId());

        model.put("productData", productDataModel);
        model.put("countries", countries);
        model.put("country", country);
        model.put("manufacturers", manufacturers);
        model.put("manufacturer", manufacturer);
        model.put("productCategory", productCategory);
        model.put("images", images);

        return "admin/product/update";
    }
}
