package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
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

    @GetMapping("/admin/reviews")
    public String getReviews(
            Map<String, Object> model,
            @RequestParam(required = false, defaultValue = "0") Integer page
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Review> reviewsPage = reviewService.getPageReview(pageable);
        model.put("reviewsPage", reviewsPage);
        return "admin/reviews";
    }

    @PostMapping("/admit/reviews/confirmation")
    public String confirmationReviews(Long id) {
        Review review = reviewService.findById(id);
        review.setStatusReview(StatusReview.ACCEPTED);
        reviewService.save(review);
        return "redirect:/admin/reviews";
    }

    @PostMapping("/admin/review/delete")
    public String deleteReview(Long id) {
        reviewService.deleteReview(id);
        return "redirect:/admin/reviews";
    }
}
