package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fwoods.computerstore.domain.Review;
import ru.fwoods.computerstore.domain.StatusReview;
import ru.fwoods.computerstore.service.ReviewService;

import java.util.Map;

@Controller
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/admin/review/update/{id}")
    public String confirmationReview(
            @PathVariable Long id,
            Map<String, Object> model
    ) {
        Review review = reviewService.findById(id);

        model.put("statuses", StatusReview.values());
        model.put("review", review);

        return "admin/support/review/update";
    }
}
