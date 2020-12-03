package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.fwoods.computerstore.domain.ProductData;
import ru.fwoods.computerstore.service.ProductDataService;

import java.util.List;

@RestController
public class ProductDataRestController {

    @Autowired
    private ProductDataService productDataService;

    @GetMapping(value = "/admin/product/get/search")
    public ResponseEntity getProductDataSearch(
            @RequestParam(required = false, defaultValue = "") String searchParam
    ) {
        List<ProductData> productDataList = productDataService.getProductDataSearch(searchParam);
        return ResponseEntity.ok().body(productDataList);
    }
}
