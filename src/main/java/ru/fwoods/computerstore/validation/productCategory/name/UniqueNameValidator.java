package ru.fwoods.computerstore.validation.productCategory.name;

import org.springframework.beans.factory.annotation.Autowired;
import ru.fwoods.computerstore.model.ProductCategory;
import ru.fwoods.computerstore.service.ProductCategoryService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, ProductCategory> {

    @Autowired
    private ProductCategoryService productCategoryService;


    @Override
    public boolean isValid(ProductCategory productCategory, ConstraintValidatorContext ctx) {
        ru.fwoods.computerstore.domain.ProductCategory productCategoryDomain = productCategoryService.getProductCategoryByName(productCategory.getName());
        if (productCategoryDomain == null || productCategoryDomain.getId().equals(productCategory.getId())) return true;

        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate("error.validation.productCategory.name.unique")
                .addPropertyNode("name")
                .addConstraintViolation();
        return false;
    }
}
