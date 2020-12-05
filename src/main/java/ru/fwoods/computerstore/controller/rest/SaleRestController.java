package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import ru.fwoods.computerstore.model.Sale;
import ru.fwoods.computerstore.service.SaleService;

@RestController
public class SaleRestController {

    @Autowired
    private SaleService saleService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/sale/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateSale(
            @RequestPart(name = "sale") Sale sale
    ) {
        saleService.saleUpdate(sale);
        return ResponseEntity.ok().body(null);
    }
}
