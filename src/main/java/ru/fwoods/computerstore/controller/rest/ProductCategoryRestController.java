package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fwoods.computerstore.controller.ControllerUtils;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.model.ProductCategory;
import ru.fwoods.computerstore.model.rest.Attribute;
import ru.fwoods.computerstore.service.AttributeService;
import ru.fwoods.computerstore.service.ProductCategoryService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ProductCategoryRestController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ControllerUtils controllerUtils;

    @Autowired
    private AttributeService attributeService;

    @PostMapping(value = "/admin/category/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCategory(
            @RequestPart(name = "category") @Validated ProductCategory productCategory,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        productCategoryService.saveProductCategory(productCategory);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/category/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCategory(
            @RequestPart(name = "category") @Validated ProductCategory productCategory,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        productCategoryService.updateCategory(productCategory);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/category/delete")
    public ResponseEntity deleteCategory(
            @RequestPart(name = "category") IdWrapper idWrapper
    ) {
        try {
            productCategoryService.deleteCategoryById(idWrapper.getId());
            return ResponseEntity.ok().body(Collections.singletonMap("errors", "Удаление успешно"));
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("errors", "Удаление невозможно, имеются связанные сущности"));
        }
    }

    @PostMapping(value = "/admin/category/{id}/attribute/create")
    public ResponseEntity createAttribute(
            @PathVariable Long id,
            @RequestPart(name = "attribute") Attribute attribute
    ) {
        attributeService.saveAttribute(attribute, id);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/category/attribute/update")
    public ResponseEntity updateAttribute(
            @RequestPart(name = "attribute") Attribute attribute
    ) {
        attributeService.update(attribute);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/category/attribute/delete")
    public ResponseEntity deleteAttribute(
            @RequestPart(name = "attribute") IdWrapper idWrapper
    ) {
        attributeService.deleteById(idWrapper.getId());
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("admin/category/parent/product/null")
    public List<ProductCategory> getCategoriesWithoutParentAndProduct() {
        return productCategoryService.getCategoriesWithoutParentAndProduct();
    }

    @GetMapping(value = "/admin/category/get/search")
    public ResponseEntity getCategorySearch(
            @RequestParam(required = false, defaultValue = "") String searchParam
    ) {
        List<ru.fwoods.computerstore.domain.ProductCategory> counties = productCategoryService.getProductCategorySearch(searchParam);
        return ResponseEntity.ok().body(counties);
    }
}
