package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.fwoods.computerstore.domain.Sale;
import ru.fwoods.computerstore.domain.SaleProduct;
import ru.fwoods.computerstore.domain.StatusSale;
import ru.fwoods.computerstore.domain.User;
import ru.fwoods.computerstore.model.ProductDataCart;
import ru.fwoods.computerstore.service.BasketService;
import ru.fwoods.computerstore.service.SaleProductService;
import ru.fwoods.computerstore.service.SaleService;

import java.util.List;
import java.util.Map;

@Controller
public class SaleController {
    @Autowired
    private SaleService saleService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private SaleProductService saleProductService;

    @GetMapping("/cart")
    public String getCart() {
        return "site/cart/index";
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("/cart/payment")
    public String getSale(
            @AuthenticationPrincipal User user,
            Map<String, Object> model
    ) {
        List<ProductDataCart> cartProducts = basketService.getProductsInBasket(user);
        model.put("cartProducts", cartProducts);
        return "site/cart/payment";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/sale/{id}/product/all")
    public String getSaleProductPage(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<SaleProduct> saleProducts = saleProductService.getSaleProductsBySale(id, pageable);

        Sale sale = saleService.getSaleById(id);

        model.put("saleProducts", saleProducts);
        model.put("sale", sale);
        model.put("statuses", StatusSale.values());
        return "admin/sale/product/all";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/sale/product/update/{id}")
    public String getUpdateSaleProductPage(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        SaleProduct saleProduct = saleProductService.getSaleProductById(id);
        model.put("saleProduct", saleProduct);
        model.put("statuses", StatusSale.values());
        return "admin/sale/product/update";
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("/store/payment")
    public String sale(
            @AuthenticationPrincipal User user,
            @RequestParam(name = "address") String address
    ) {
        saleService.sale(user, address);
        return "redirect:/";
    }
}
