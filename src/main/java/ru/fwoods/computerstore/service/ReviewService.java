package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.Review;
import ru.fwoods.computerstore.repository.ReviewRepository;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

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
}
