package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.computerstore.domain.Image;
import ru.fwoods.computerstore.domain.Promotion;
import ru.fwoods.computerstore.domain.PromotionProduct;
import ru.fwoods.computerstore.model.DiscountProduct;
import ru.fwoods.computerstore.repository.PromotionRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class PromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private FileService fileService;

    @Autowired
    private ProductDataService productDataService;

    @Autowired
    private PromotionProductService promotionProductService;

    @Autowired
    private ImageService imageService;

    @Value("${promotion.upload.path}")
    private String promotionUploadPath;

    public List<Promotion> getAllPromotion() {
        return promotionRepository.findAll();
    }

    public void save(Promotion promotion) {
        promotionRepository.save(promotion);
    }

    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }

    private String savePromotionBanner(MultipartFile file) {
        return fileService.createFile(promotionUploadPath, file);
    }

    public void savePromotion(ru.fwoods.computerstore.model.Promotion promotion, List<MultipartFile> promotionBanner) {
        Promotion promotionDomain = new Promotion();

        promotionDomain.setName(promotion.getName());
        promotionDomain.setDescription(promotion.getDescription());
        promotionDomain.setDateStart(promotion.getDateStart());
        promotionDomain.setDateEnd(promotion.getDateEnd());

        Promotion promotionDomainSaved = promotionRepository.save(promotionDomain);

        if (promotionBanner != null && !promotionBanner.isEmpty()) {
            promotionBanner.forEach(banner -> {
                if (!Objects.equals(banner.getContentType(), "null")) {
                    Image image = new Image();
                    String bannerFilename = savePromotionBanner(banner);
                    image.setFilename(bannerFilename);
                    image.setPromotion(promotionDomainSaved);
                    imageService.save(image);
                }
            });
        }

        savePromotionProduct(promotion, promotionDomain, promotionDomainSaved);
    }

    private void savePromotionProduct(ru.fwoods.computerstore.model.Promotion promotion, Promotion promotionDomain, Promotion promotionDomainSaved) {
        List<PromotionProduct> promotionProducts = promotion.getProducts().stream().map(discountProduct -> {
            PromotionProduct promotionProduct = new PromotionProduct();
            promotionProduct.setDiscount(discountProduct.getDiscount());
            promotionProduct.setPromotion(promotionDomainSaved);
            promotionProduct.setProductData(productDataService.getProductDataById(discountProduct.getProduct()));
            return promotionProductService.save(promotionProduct);
        }).collect(Collectors.toList());

        promotionDomain.setPromotionProducts(promotionProducts);

        promotionRepository.save(promotionDomain);
    }

    public Page<Promotion> getPagePromotion(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }

    public Promotion getPromotionByName(String name) {
        return promotionRepository.getPromotionByName(name);
    }

    public void updatePromotion(ru.fwoods.computerstore.model.Promotion promotion, List<MultipartFile> promotionBanner) {
        Promotion promotionDomain = promotionRepository.getOne(promotion.getId());

        promotionDomain.setName(promotion.getName());
        promotionDomain.setDescription(promotion.getDescription());
        promotionDomain.setDateStart(promotion.getDateStart());
        promotionDomain.setDateEnd(promotion.getDateEnd());

        Promotion promotionDomainSaved = promotionRepository.save(promotionDomain);

        if (promotionBanner != null && !promotionBanner.isEmpty()) {
            promotionBanner.forEach(banner -> {
                if (!Objects.equals(banner.getContentType(), "null")) {
                    if (banner.getOriginalFilename() != null) {
                        Image image = new Image();
                        String bannerFilename = savePromotionBanner(banner);
                        image.setFilename(bannerFilename);
                        image.setPromotion(promotionDomainSaved);
                        imageService.save(image);
                    }
                }
            });
        }

        promotionDomain.getPromotionProducts().forEach(promotionProduct -> {
            promotionProductService.delete(promotionProduct);
        });

        savePromotionProduct(promotion, promotionDomain, promotionDomainSaved);
    }

    public ru.fwoods.computerstore.model.Promotion getPromotionModelById(Long id) {
        Promotion promotionDomain = promotionRepository.getOne(id);
        ru.fwoods.computerstore.model.Promotion promotionModel = new ru.fwoods.computerstore.model.Promotion();

        promotionModel.setId(promotionDomain.getId());
        promotionModel.setName(promotionDomain.getName());
        promotionModel.setDescription(promotionDomain.getDescription());
        promotionModel.setDateStart(promotionDomain.getDateStart());
        promotionModel.setDateEnd(promotionDomain.getDateEnd());

        List<DiscountProduct> discountProducts = promotionDomain.getPromotionProducts().stream().map(promotionProduct -> {
            DiscountProduct discountProduct = new DiscountProduct();
            discountProduct.setProduct(promotionProduct.getProductData().getId());
            discountProduct.setName(promotionProduct.getProductData().getName());
            discountProduct.setDiscount(promotionProduct.getDiscount());
            return  discountProduct;
        }).collect(Collectors.toList());

        promotionModel.setProducts(discountProducts);

        return promotionModel;
    }

    public Promotion findById(Long id) {
        return promotionRepository.getOne(id);
    }
}
