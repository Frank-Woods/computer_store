package ru.fwoods.computerstore.validation.manufacturer.name;

import org.springframework.beans.factory.annotation.Autowired;
import ru.fwoods.computerstore.model.Manufacturer;
import ru.fwoods.computerstore.service.ManufacturerService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, Manufacturer> {

    @Autowired
    private ManufacturerService manufacturerService;


    @Override
    public boolean isValid(Manufacturer manufacturer, ConstraintValidatorContext ctx) {
        ru.fwoods.computerstore.domain.Manufacturer manufacturerDomain = manufacturerService.getManufacturerByName(manufacturer.getName());
        if (manufacturerDomain == null || manufacturerDomain.getId().equals(manufacturer.getId())) return true;

        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate("error.validation.manufacturer.name.unique")
                .addPropertyNode("name")
                .addConstraintViolation();
        return false;
    }
}
