package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping
    public String getAdminPanel() {
        return "admin";
    }

    @GetMapping("/countries")
    public String getCountriesPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Country> countriesPage = countryService.getPageCountries(pageable);
        model.put("countriesPage", countriesPage);
        return "admin/countries";
    }

    @GetMapping("/manufacturers")
    public String getManufacturersPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Manufacturer> manufacturersPage = manufacturerService.getPageManufacturers(pageable);
        model.put("manufacturersPage", manufacturersPage);
        return "admin/manufacturers";
    }

    @GetMapping("/categories")
    public String getCategoriesPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<ProductCategory> categoriesPage = productCategoryService.getPageCategories(pageable);
        model.put("categoriesPage", categoriesPage);
        return "admin/categories";
    }

    @GetMapping("/sales")
    public String getSalesPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Sale> salesPage = saleService.getPageSales(pageable);
        model.put("sales", salesPage);
        return "admin/sales";
    }

    @GetMapping("/products")
    public String getProductsPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<ProductData> productsPage = productDataService.getPageProducts(pageable);
        model.put("products", productsPage);
        return "admin/products";
    }

    @GetMapping("/promotions")
    public String getPromotionsPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Promotion> promotionsPage = promotionService.getPagePromotion(pageable);
        model.put("promotions", promotionsPage);
        return "admin/promotions";
    }

    @GetMapping("/users")
    public String getUsers(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<User> usersPage = userService.getPageUser(pageable);
        model.put("users", usersPage);
        return "admin/users";
    }
}
