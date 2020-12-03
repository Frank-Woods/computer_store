package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.computerstore.controller.ControllerUtils;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.model.Promotion;
import ru.fwoods.computerstore.service.PromotionService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class PromotionRestController {

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ControllerUtils controllerUtils;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "admin/promotion/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createPromotion(
            @RequestPart(name = "promotion", required = false) @Validated Promotion promotion,
            @RequestPart(name = "promotionBanner", required = false) List<MultipartFile> promotionBanner,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        promotionService.savePromotion(promotion, promotionBanner);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "admin/promotion/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updatePromotion(
            @RequestPart(name = "promotion") @Validated Promotion promotion,
            @RequestPart(name = "promotionBanner", required = false) List<MultipartFile> promotionBanner,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        promotionService.updatePromotion(promotion, promotionBanner);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/promotion/delete")
    public ResponseEntity<Map<String, String>> deletePromotion(@RequestPart(name = "promotion") IdWrapper idWrapper) {
        try {
            promotionService.deletePromotion(idWrapper.getId());
            return ResponseEntity.ok().body(null);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Удаление невозможно, есть связанные сущности"));
        }
    }
}
