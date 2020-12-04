package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.computerstore.domain.Image;
import ru.fwoods.computerstore.domain.Manufacturer;
import ru.fwoods.computerstore.domain.Promotion;
import ru.fwoods.computerstore.domain.PromotionProduct;
import ru.fwoods.computerstore.model.DiscountProduct;
import ru.fwoods.computerstore.repository.PromotionRepository;

import java.util.ArrayList;
import java.util.Date;
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

    public void deletePromotion(Long id) {
        Promotion manufacturerDomain = promotionRepository.getOne(id);
        List<Image> promotionImages = imageService.getImagesByManufacturerId(manufacturerDomain.getId());

        promotionImages.forEach(manufacturerImage -> {
            imageService.deleteManufacturerImage(manufacturerImage.getId());
        });

        promotionRepository.deleteById(id);
    }

    public void savePromotion(ru.fwoods.computerstore.model.Promotion promotion, List<MultipartFile> images) {
        Promotion promotionDomain = new Promotion();

        promotionDomain.setName(promotion.getName());
        promotionDomain.setDescription(promotion.getDescription());
        promotionDomain.setDateStart(promotion.getDateStart());
        promotionDomain.setDateEnd(promotion.getDateEnd());

        Promotion promotionDomainSaved = promotionRepository.save(promotionDomain);

        if (images != null && !images.isEmpty()) {
            images.forEach(image -> imageService.savePromotionImage(image, promotionDomainSaved));
        }

        promotionRepository.save(promotionDomain);
    }

    public Page<Promotion> getPagePromotion(Pageable pageable) {
        return promotionRepository.findAll(pageable);
    }

    public Promotion getPromotionByName(String name) {
        return promotionRepository.getPromotionByName(name);
    }

    public void updatePromotion(ru.fwoods.computerstore.model.Promotion promotion, List<MultipartFile> images) {
        Promotion promotionDomain = promotionRepository.getOne(promotion.getId());

        promotionDomain.setName(promotion.getName());
        promotionDomain.setDescription(promotion.getDescription());
        promotionDomain.setDateStart(promotion.getDateStart());
        promotionDomain.setDateEnd(promotion.getDateEnd());

        List<Image> promotionImages = imageService.getImagesByPromotionId(promotionDomain.getId());
        List<Long> imageIds = new ArrayList<>();

        if (images != null && !images.isEmpty()) {
            images.forEach(image -> {
                if (!Objects.equals(image.getContentType(), "null")) {
                    imageService.savePromotionImage(image, promotionDomain);
                } else {
                    try {
                        Long id = Long.parseLong(image.getOriginalFilename());
                        imageIds.add(id);
                    } catch (NullPointerException | NumberFormatException exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }

        promotionImages.forEach(promotionImage -> {
            if (!imageIds.contains(promotionImage.getId())) {
                imageService.deletePromotionImage(promotionImage.getId());
            }
        });

        promotionRepository.save(promotionDomain);
    }

    public Promotion findById(Long id) {
        return promotionRepository.getOne(id);
    }

    public Promotion findActivePromotion() {
        List<Promotion> promotions = promotionRepository.findAll();
        for (Promotion promotion : promotions) {
            if (promotion.getDateEnd().getTime() > new Date().getTime()) {
                return promotion;
            }
        }
        return null;
    }

    public Promotion getPromotionById(Long id) {
        return promotionRepository.getOne(id);
    }

    public List<Promotion> findAll() {
        return promotionRepository.findAll();
    }
}
