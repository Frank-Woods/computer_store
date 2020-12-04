package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.computerstore.controller.ControllerUtils;
import ru.fwoods.computerstore.domain.User;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.model.Review;
import ru.fwoods.computerstore.service.ReviewService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ReviewRestController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ControllerUtils controllerUtils;

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/review/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createReview(
            @AuthenticationPrincipal User user,
            @RequestPart(name = "review", required = false) Review review,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        reviewService.saveReview(user, review);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/review/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateReview(
            @AuthenticationPrincipal User user,
            @RequestPart(name = "review", required = false) Review review,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        reviewService.updateReview(user, review);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/review/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity adminUpdateReview(
            @AuthenticationPrincipal User user,
            @RequestPart(name = "review", required = false) Review review,
            @RequestPart(name = "reviewImages", required = false) List<MultipartFile> images,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        reviewService.updateReviewStatus(review);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping(value = "/review/delete")
    public ResponseEntity<Map<String, String>> deletePromotion(@RequestPart(name = "review") IdWrapper idWrapper) {
        try {
            reviewService.deleteReview(idWrapper.getId());
            return ResponseEntity.ok().body(null);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Удаление невозможно, есть связанные сущности"));
        }
    }
}
