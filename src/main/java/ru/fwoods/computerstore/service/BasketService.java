package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.*;
import ru.fwoods.computerstore.model.CartProduct;
import ru.fwoods.computerstore.model.ProductDataCart;
import ru.fwoods.computerstore.repository.BasketRepository;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ProductDataService productDataService;

    @Autowired
    private PromotionProductService promotionProductService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private UserService userService;

    public ProductDataCart save(User user, CartProduct product) {
        List<Basket> baskets = basketRepository.getAllByUserId(user.getId());

        Basket basket = baskets.stream()
                .filter(p -> p.getProductData().getId().equals(product.getId()))
                .findFirst().orElse(new Basket(user, productDataService.getProductDataById(product.getId())));

        basket.setCount(product.getCount());

        if (basket.getId() == null) {
            basketRepository.save(basket);

            ProductDataCart productDataCart = new ProductDataCart();

            ProductData productData = productDataService.getProductDataById(product.getId());

            productDataCart.setId(productData.getId());
            productDataCart.setName(productData.getName());
            Image image = imageService.getFirstImageByProductDataId(productData.getId());
            if (image != null) {
                productDataCart.setImage(image.getFilename());
            }
            productDataCart.setCount(product.getCount());
            productDataCart.setCost(productData.getCost());

            PromotionProduct promotionProduct = promotionProductService.getPromotionProductByProductData(productData);
            Integer discountPrice = productData.getCost();
            if (promotionProduct != null) {
                if (new Date().getTime() < promotionProduct.getPromotion().getDateEnd().getTime()) {
                    discountPrice = productData.getCost() * promotionProduct.getDiscount() / 100;
                    productDataCart.setDiscount(promotionProduct.getDiscount());
                }
            }
            productDataCart.setDiscountCost(discountPrice);

            return productDataCart;
        } else {
            return null;
        }
    }

    public List<CartProduct> getAllCartProduct(User user) {
        List<Basket> baskets = basketRepository.getAllByUserId(user.getId());

        return baskets.stream().map(basket -> {
            CartProduct cartProduct = new CartProduct();
            cartProduct.setId(basket.getProductData().getId());
            cartProduct.setCount(basket.getCount());
            return cartProduct;
        }).collect(Collectors.toList());
    }

    public List<ProductDataCart> getProductsInBasket(User user) {
        List<Basket> baskets = basketRepository.getAllByUserId(user.getId());

        List<CartProduct> cartProducts = baskets.stream().map(basket -> new CartProduct(basket.getProductData().getId(), basket.getCount())).collect(Collectors.toList());

        return getProductsInBasket(cartProducts);
    }

    public List<ProductDataCart> getProductsInBasket(List<CartProduct> products) {

        return products.stream().map(product -> {
            ProductDataCart productDataCart = new ProductDataCart();

            ProductData productData = productDataService.getProductDataById(product.getId());

            productDataCart.setId(productData.getId());
            productDataCart.setName(productData.getName());
            Image image = imageService.getFirstImageByProductDataId(productData.getId());
            if (image != null) {
                productDataCart.setImage(image.getFilename());
            }
            productDataCart.setCount(product.getCount());
            productDataCart.setCost(productData.getCost());

            PromotionProduct promotionProduct = promotionProductService.getPromotionProductByProductData(productData);
            Integer discountPrice = productData.getCost();
            if (promotionProduct != null) {
                if (new Date().getTime() < promotionProduct.getPromotion().getDateEnd().getTime()) {
                    discountPrice = productData.getCost() * promotionProduct.getDiscount() / 100;
                    productDataCart.setDiscount(promotionProduct.getDiscount());
                }
            } else {
                productDataCart.setDiscount(0);
            }
            productDataCart.setDiscountCost(discountPrice);

            return productDataCart;
        }).collect(Collectors.toList());
    }

    public void delete(User user, CartProduct product) {
        List<Basket> baskets = basketRepository.getAllByUserId(user.getId());

        Basket basket = baskets.stream()
                .filter(p -> p.getProductData().getId().equals(product.getId()))
                .findFirst().orElse(null);

        if (basket != null) {
            basketRepository.delete(basket);
        }
    }

    public void deleteById(Long id) {
        basketRepository.deleteById(id);
    }

    public Integer getCountProduct(User authUser) {
        User user = userService.getUserById(authUser.getId());
        return user.getBaskets().size();
    }
}
