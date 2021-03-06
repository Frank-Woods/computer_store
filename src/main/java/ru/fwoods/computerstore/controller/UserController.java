package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.fwoods.computerstore.domain.*;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.service.*;

import java.util.List;
import java.util.Map;

@Controller
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

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/profile/details")
    public String getUserProfilePage(Map<String, Object> model) {
        List<City> cities = cityService.getAllCities();
        model.put("cities", cities);
        return "site/user/profile/details";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/profile/reviews")
    public String getReviews(
            @AuthenticationPrincipal User user,
            Map<String, Object> model,
            @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Review> reviews = reviewService.getReviewsByUser(user, pageable);
        model.put("reviewsPage", reviews);
        return "site/user/profile/reviews";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/profile/reviews/{id}")
    public String getReview(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Review review = reviewService.findById(id);
        model.put("review", review);
        return "site/user/profile/review";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/profile/orders")
    public String getSales(
            @AuthenticationPrincipal User user,
            Map<String, Object> model,
            @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Sale> sales = saleService.getSalesPageByUser(user.getId(), pageable);
        model.put("salesPage", sales);
        return "site/user/profile/orders";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/user/profile/orders/{id}/product/all")
    public String getSale(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model,
            @PathVariable Long id
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<SaleProduct> sales = saleProductService.getSaleProductsBySale(id, pageable);
        Sale sale = sales.getContent().get(0).getSale();

        model.put("sale", sale);
        model.put("sales", sales);
        return "site/user/profile/orders/products";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/user/add")
    public String addAdmin(
            @RequestParam(name = "id") Long id
    ) {
        userService.addAdmin(id);
        return "redirect:/admin/user/all";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/admin/user/dismiss")
    public String deleteAdmin(
            @RequestParam(name = "id") Long id
    ) {
        userService.deleteAdmin(id);
        return "redirect:/admin/user/all";
    }
}
