package ru.fwoods.computerstore.validation.promotion.name;

import org.springframework.beans.factory.annotation.Autowired;
import ru.fwoods.computerstore.model.Promotion;
import ru.fwoods.computerstore.service.PromotionService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueNameValidator implements ConstraintValidator<UniqueName, Promotion> {

    @Autowired
    private PromotionService promotionService;


    @Override
    public boolean isValid(Promotion promotion, ConstraintValidatorContext ctx) {
        ru.fwoods.computerstore.domain.Promotion promotionDomain = promotionService.getPromotionByName(promotion.getName());
        if (promotionDomain == null || promotionDomain.getId().equals(promotion.getId())) return true;

        ctx.disableDefaultConstraintViolation();
        ctx.buildConstraintViolationWithTemplate("error.validation.promotion.name.unique")
                .addPropertyNode("name")
                .addConstraintViolation();
        return false;
    }
}
