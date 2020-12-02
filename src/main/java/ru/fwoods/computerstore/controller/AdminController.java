package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fwoods.computerstore.domain.*;
import ru.fwoods.computerstore.service.*;

import java.util.Map;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private ProductDataService productDataService;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @GetMapping
    public String getAdminPanel() {
        return "admin/index";
    }

    @GetMapping("/country/all")
    public String getCountriesPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 1);
        Page<Country> countriesPage = countryService.getPageCountries(pageable);
        model.put("countriesPage", countriesPage);
        return "admin/country/all";
    }

    @GetMapping("/city/all")
    public String getCitiesPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<City> citiesPage = cityService.getPageCities(pageable);
        model.put("citiesPage", citiesPage);
        return "admin/city/all";
    }

    @GetMapping("/manufacturer/all")
    public String getManufacturersPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Manufacturer> manufacturersPage = manufacturerService.getPageManufacturers(pageable);
        model.put("manufacturersPage", manufacturersPage);
        return "admin/manufacturer/all";
    }

    @GetMapping("/category/all")
    public String getCategoriesPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<ProductCategory> categoriesPage = productCategoryService.getPageCategories(pageable);
        model.put("categoriesPage", categoriesPage);
        return "admin/category/all";
    }

    @GetMapping("/sale/all")
    public String getSalesPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Sale> salesPage = saleService.getPageSales(pageable);
        model.put("sales", salesPage);
        return "admin/sale/all";
    }

    @GetMapping("/product/all")
    public String getProductsPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<ProductData> productsPage = productDataService.getPageProducts(pageable);
        model.put("products", productsPage);
        return "admin/product/all";
    }

    @GetMapping("/promotion/all")
    public String getPromotionsPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Promotion> promotionsPage = promotionService.getPagePromotion(pageable);
        model.put("promotions", promotionsPage);
        return "admin/promotion/all";
    }

    @GetMapping("/user/all")
    public String getUsers(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<User> usersPage = userService.getPageUser(pageable);
        model.put("users", usersPage);
        return "admin/user/all";
    }
}
