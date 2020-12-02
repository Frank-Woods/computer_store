package ru.fwoods.computerstore.validation.productData.name;

import org.springframework.beans.factory.annotation.Autowired;
import ru.fwoods.computerstore.model.ProductData;
import ru.fwoods.computerstore.service.ProductDataService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, ProductData> {

    @Autowired
    private ProductDataService productDataService;


    @Override
    public boolean isValid(ProductData productData, ConstraintValidatorContext ctx) {
        ru.fwoods.computerstore.domain.ProductData productDataDomain = productDataService.getProductDataByName(productData.getName());
        if (productDataDomain == null || productDataDomain.getId().equals(productData.getId())) return true;

        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate("error.validation.productData.name.unique")
                .addPropertyNode("name")
                .addConstraintViolation();
        return false;
    }
}
