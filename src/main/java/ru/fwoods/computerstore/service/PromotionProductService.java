package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.PromotionProduct;
import ru.fwoods.computerstore.model.DiscountProduct;
import ru.fwoods.computerstore.repository.PromotionProductRepository;

import java.util.List;
import java.util.stream.Collectors;

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

    public List<DiscountProduct> getAllByPromotionId(Long id) {
        List<PromotionProduct> promotionProducts = promotionProductRepository.getAllByPromotionId(id);

        return promotionProducts.stream().map(promotionProduct -> {
            DiscountProduct discountProduct = new DiscountProduct();

            discountProduct.setProduct(promotionProduct.getProductData().getId());
            discountProduct.setName(promotionProduct.getProductData().getName());
            discountProduct.setDiscount(promotionProduct.getDiscount());

            return discountProduct;
        }).collect(Collectors.toList());
    }

    public DiscountProduct getDiscountProductById(Long id) {
        PromotionProduct promotionProduct = promotionProductRepository.getOne(id);

        DiscountProduct discountProduct = new DiscountProduct();
        discountProduct.setProduct(promotionProduct.getProductData().getId());
        discountProduct.setName(promotionProduct.getProductData().getName());
        discountProduct.setDiscount(promotionProduct.getDiscount());

        return discountProduct;
    }
}
