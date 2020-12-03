package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fwoods.computerstore.controller.ControllerUtils;
import ru.fwoods.computerstore.model.DiscountProduct;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.model.ProductCategory;
import ru.fwoods.computerstore.model.rest.Attribute;
import ru.fwoods.computerstore.service.AttributeService;
import ru.fwoods.computerstore.service.ProductCategoryService;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class ProductCategoryRestController {

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ControllerUtils controllerUtils;

    @Autowired
    private AttributeService attributeService;

    @PostMapping(value = "/admin/productCategory/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createCategory(
            @RequestPart(name = "productCategory") @Validated ProductCategory productCategory,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        productCategoryService.saveProductCategory(productCategory);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/productCategory/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateCategory(
            @RequestPart(name = "productCategory") @Validated ProductCategory productCategory,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        productCategoryService.updateCategory(productCategory);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/productCategory/delete")
    public ResponseEntity deleteCategory(
            @RequestPart(name = "productCategory") IdWrapper idWrapper
    ) {
        try {
            productCategoryService.deleteCategoryById(idWrapper.getId());
            return ResponseEntity.ok().body(Collections.singletonMap("errors", "Удаление успешно"));
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("errors", "Удаление невозможно, имеются связанные сущности"));
        }
    }

    @PostMapping(value = "/admin/productCategory/{id}/attribute/create")
    public ResponseEntity createAttribute(
            @RequestPart(name = "productCategory") IdWrapper idWrapper,
            @RequestPart(name = "attribute") Attribute attribute
    ) {
        attributeService.saveAttribute(attribute, idWrapper);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/productCategory/attribute/update")
    public ResponseEntity updateAttribute(
            @RequestPart(name = "attribute") Attribute attribute
    ) {
        attributeService.update(attribute);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/admin/productCategory/parent/product/null")
    public List<ProductCategory> getCategoriesWithoutParentAndProduct() {
        return productCategoryService.getCategoriesWithoutParentAndProduct();
    }
}
