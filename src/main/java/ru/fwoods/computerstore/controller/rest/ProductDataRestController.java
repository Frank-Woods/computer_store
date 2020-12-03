package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.computerstore.controller.ControllerUtils;
import ru.fwoods.computerstore.domain.ProductData;
import ru.fwoods.computerstore.service.ProductDataService;

import java.util.List;
import java.util.Map;

@RestController
public class ProductDataRestController {

    @Autowired
    private ProductDataService productDataService;

    @Autowired
    private ControllerUtils controllerUtils;

    @GetMapping(value = "/admin/product/get/search")
    public ResponseEntity getProductDataSearch(
            @RequestParam(required = false, defaultValue = "") String searchParam
    ) {
        List<ProductData> productDataList = productDataService.getProductDataSearch(searchParam);
        return ResponseEntity.ok().body(productDataList);
    }

    @PostMapping(value = "/admin/product/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createProduct(
            @RequestPart(name = "product", required = false) @Validated ru.fwoods.computerstore.model.ProductData productData,
            @RequestPart(name = "productImage", required = false) List<MultipartFile> images,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        productDataService.save(productData, images);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/product/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateProduct(
            @RequestPart(name = "product", required = false) @Validated ru.fwoods.computerstore.model.ProductData productData,
            @RequestPart(name = "productImages", required = false) List<MultipartFile> images,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        productDataService.update(productData, images);
        return ResponseEntity.ok().body(null);
    }
}
