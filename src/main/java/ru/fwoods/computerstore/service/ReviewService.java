package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.Review;
import ru.fwoods.computerstore.domain.StatusReview;
import ru.fwoods.computerstore.domain.User;
import ru.fwoods.computerstore.repository.ReviewRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private ProductDataService productDataService;

    public List<Object> getProductsRating() {
        return reviewRepository.getProductsRating();
    }

    public Page<Review> getPageReview(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    public Review findById(Long id) {
        return reviewRepository.getOne(id);
    }

    public void save(Review review) {
        reviewRepository.save(review);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public Review getReviewByUserAndProductData(Long user, Long productData) {
        return reviewRepository.getByUserIdAndProductDataId(user, productData);
    }

    public void saveReview(User user, ru.fwoods.computerstore.model.Review review) {
        Review reviewDomain = new Review();

        reviewDomain.setAdvantages(review.getAdvantages());
        reviewDomain.setDisadvantages(review.getDisadvantages());
        reviewDomain.setComment(review.getComment());
        reviewDomain.setDate(new Date());
        reviewDomain.setProductData(productDataService.getProductDataById(review.getProductData()));
        reviewDomain.setUser(user);
        reviewDomain.setRating(review.getRating());
        reviewDomain.setStatusReview(StatusReview.PROCESSING);

        reviewRepository.save(reviewDomain);
    }

    public void updateReview(User user, ru.fwoods.computerstore.model.Review review) {
        Review reviewDomain = reviewRepository.getOne(review.getId());

        reviewDomain.setAdvantages(review.getAdvantages());
        reviewDomain.setDisadvantages(review.getDisadvantages());
        reviewDomain.setComment(review.getComment());
        reviewDomain.setRating(review.getRating());
        reviewDomain.setStatusReview(StatusReview.PROCESSING);

        reviewRepository.save(reviewDomain);
    }

    public void updateReviewStatus(ru.fwoods.computerstore.model.Review review) {
        Review reviewDomain = reviewRepository.getOne(review.getId());
        reviewDomain.setStatusReview(
                Arrays.stream(StatusReview.values())
                        .filter(statusReview -> statusReview.ordinal() == review.getStatusReview())
                        .findFirst()
                        .orElse(null)
        );
        reviewRepository.save(reviewDomain);
    }

    public Page<Review> getReviewsByUser(User user, Pageable pageable) {
        return reviewRepository.getAllByUserId(user.getId(), pageable);
    }
}
