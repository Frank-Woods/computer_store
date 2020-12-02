package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.PromotionProduct;
import ru.fwoods.computerstore.repository.PromotionProductRepository;

@Service
public class PromotionProductService {
    @Autowired
    private PromotionProductRepository promotionProductRepository;

    public PromotionProduct save(PromotionProduct promotionProduct) {
        return promotionProductRepository.save(promotionProduct);
    }

    public void delete(PromotionProduct promotionProduct) {
        promotionProductRepository.delete(promotionProduct);
    }
}
