package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.PromotionProduct;
import ru.fwoods.computerstore.model.DiscountProduct;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.repository.PromotionProductRepository;

import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PromotionProductService {

    @Autowired
    private PromotionProductRepository promotionProductRepository;

    @Autowired
    private PromotionService promotionService;

    @Autowired
    private ProductDataService productDataService;

    public PromotionProduct save(DiscountProduct discountProduct, Long id) {
        PromotionProduct promotionProduct = new PromotionProduct();

        promotionProduct.setProductData(productDataService.getProductDataById(discountProduct.getProduct()));
        promotionProduct.setPromotion(promotionService.findById(id));
        promotionProduct.setDiscount(discountProduct.getDiscount());

        return promotionProductRepository.save(promotionProduct);
    }

    public void delete(PromotionProduct promotionProduct) {
        promotionProductRepository.delete(promotionProduct);
    }

    public DiscountProduct getDiscountProductById(Long id) {
        PromotionProduct promotionProduct = promotionProductRepository.getOne(id);

        DiscountProduct discountProduct = new DiscountProduct();
        discountProduct.setProduct(promotionProduct.getProductData().getId());
        discountProduct.setName(promotionProduct.getProductData().getName());
        discountProduct.setDiscount(promotionProduct.getDiscount());

        return discountProduct;
    }

    public void update(DiscountProduct discountProduct) {
        PromotionProduct promotionProduct = promotionProductRepository.getOne(discountProduct.getId());

        promotionProduct.setProductData(productDataService.getProductDataById(discountProduct.getProduct()));
        promotionProduct.setDiscount(discountProduct.getDiscount());

        promotionProductRepository.save(promotionProduct);
    }

    public Page<DiscountProduct> getAllByPromotionIdPage(Long id, Pageable pageable) {
        Page<PromotionProduct> promotionProducts = promotionProductRepository.getAllByPromotionId(id, pageable);

        return promotionProducts.map(promotionProduct -> {
            DiscountProduct discountProduct = new DiscountProduct();

            discountProduct.setId(promotionProduct.getId());
            discountProduct.setProduct(promotionProduct.getProductData().getId());
            discountProduct.setName(promotionProduct.getProductData().getName());
            discountProduct.setDiscount(promotionProduct.getDiscount());

            return discountProduct;
        });
    }

    public void deleteById(Long id) {
        promotionProductRepository.deleteById(id);
    }
}
