package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.fwoods.computerstore.domain.Sale;
import ru.fwoods.computerstore.service.SaleService;

import java.util.Map;

@Controller
public class SaleController {
    @Autowired
    private SaleService saleService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/sales/{id}")
    public String getSalePage(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Sale sale = saleService.getSaleById(id);
        model.put("sale", sale);
        return "sale/info";
    }
}
