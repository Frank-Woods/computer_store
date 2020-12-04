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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fwoods.computerstore.domain.*;
import ru.fwoods.computerstore.service.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private SaleProductService saleProductService;

    @GetMapping("/profile")
    public String getUserProfilePage(Map<String, Object> model) {
        List<City> cities = cityService.getAllCities();
        model.put("cities", cities);
        return "site/user/profile";
    }

    @GetMapping("/profile/password")
    public String getUserProfilePasswordPage() {
        return "site/user/changePassword";
    }

    @GetMapping("/profile/reviews")
    public String getReviews(
            @AuthenticationPrincipal User user,
            Map<String, Object> model,
            @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Review> reviews = reviewService.getReviewsByUser(user, pageable);
        model.put("reviewsPage", reviews);
        return "site/user/review";
    }

    @GetMapping("/profile/reviews/{id}")
    public String getReview(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Review review = reviewService.findById(id);
        model.put("review", review);
        return "site/user/review";
    }

    @GetMapping("/profile/sales")
    public String getSales(
            @AuthenticationPrincipal User user,
            Map<String, Object> model,
            @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Sale> sales = saleService.getSalesPageByUser(user.getId(), pageable);
        model.put("salesPage", sales);
        return "site/user/sale";
    }

    @GetMapping("/profile/sales/{id}")
    public String getSale(
            @AuthenticationPrincipal User user,
            Map<String, Object> model,
            @PathVariable Long id
    ) {
        List<SaleProduct> sales = saleProductService.getSaleProductsBySale(id);
        model.put("sales", sales);
        return "site/sale/index";
    }
}