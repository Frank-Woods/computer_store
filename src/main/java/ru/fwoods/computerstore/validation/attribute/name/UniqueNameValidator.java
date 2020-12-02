package ru.fwoods.computerstore.validation.attribute.name;

import org.springframework.beans.factory.annotation.Autowired;
import ru.fwoods.computerstore.model.Attribute;
import ru.fwoods.computerstore.service.AttributeService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, Attribute> {

    @Autowired
    private AttributeService attributeService;


    @Override
    public boolean isValid(Attribute attribute, ConstraintValidatorContext ctx) {
        ru.fwoods.computerstore.domain.Attribute attributeDomain = attributeService.getAttributeByName(attribute.getName());
        if (attributeDomain == null || attributeDomain.getId().equals(attribute.getId())) return true;

        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate("error.validation.attribute.name.unique")
                .addPropertyNode("name")
                .addConstraintViolation();
        return false;
    }
}
