package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.computerstore.domain.*;
import ru.fwoods.computerstore.repository.ProductDataRepository;

import java.util.*;

@Service
public class ProductDataService {
    @Autowired
    private ProductDataRepository productDataRepository;

    @Autowired
    private CountryService countryService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private AttributeCategoryService attributeCategoryService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ValueService valueService;

    @Autowired
    private AttributeValueService attributeValueService;

    @Autowired
    private FileService fileService;

    @Autowired
    private ImageService imageService;

    @org.springframework.beans.factory.annotation.Value("${productData.upload.path}")
    private String productDataUploadPath;

    public List<ProductData> getAllProducts() {
        return productDataRepository.findAll();
    }

    private String saveProductDataImage(MultipartFile file) {
        return fileService.createFile(productDataUploadPath, file);
    }

    public void save(ru.fwoods.computerstore.model.ProductData productData, List<MultipartFile> images) {
        ProductData productDataDomain = new ProductData();

        productDataDomain.setName(productData.getName());
        productDataDomain.setDescription(productData.getDescription());
        productDataDomain.setCost(productData.getCost());
        productDataDomain.setCategory(productCategoryService.getCategoryById(productData.getCategory()));
        productDataDomain.setCountry(countryService.getCountryById(productData.getCountry()));
        productDataDomain.setManufacturer(manufacturerService.getManufacturerById(productData.getManufacturer()));

        ProductData productDataDomainSaved = productDataRepository.save(productDataDomain);

        if (images != null && !images.isEmpty()) {
            images.forEach(image -> imageService.saveProductDataImage(image, productDataDomainSaved));
        }

        productDataRepository.save(productDataDomainSaved);
    }

    public Page<ProductData> getPageProducts(Pageable pageable) {
        return productDataRepository.findAll(pageable);
    }

    public Page<ru.fwoods.computerstore.model.rest.ProductData> getPageProductsSearch(Pageable pageable, String searchParam, List<Long> id) {
        Page<ProductData> productData;
        if (id == null || id.isEmpty()) {
            if (searchParam.isEmpty()) {
                productData = productDataRepository.findAll(pageable);
            } else {
                productData = productDataRepository.findAllByNameContainsIgnoreCase(searchParam, pageable);
            }
        } else {
            if (searchParam.isEmpty()) {
                productData = productDataRepository.getAllByIdNotIn(id, pageable);
            } else {
                productData = productDataRepository.findAllByNameContainsIgnoreCaseAndIdNotIn(searchParam, id, pageable);
            }
        }
        return productData.map(product -> {
            ru.fwoods.computerstore.model.rest.ProductData productDataRest = new ru.fwoods.computerstore.model.rest.ProductData();
            productDataRest.setId(product.getId());
            productDataRest.setName(product.getName());
            return productDataRest;
        });
    }

    public ProductData getProductDataByName(String name) {
        return productDataRepository.getProductDataByName(name);
    }

    public ProductData getProductDataById(Long id) {
        return productDataRepository.findById(id).orElse(null);
    }

    public void update(ru.fwoods.computerstore.model.ProductData productData, List<MultipartFile> images) {
        ProductData productDataDomain = productDataRepository.getOne(productData.getId());

        productDataDomain.setName(productData.getName());
        productDataDomain.setDescription(productData.getDescription());
        productDataDomain.setCost(productData.getCost());
        productDataDomain.setCategory(productCategoryService.getCategoryById(productData.getCategory()));
        productDataDomain.setCountry(countryService.getCountryById(productData.getCountry()));
        productDataDomain.setManufacturer(manufacturerService.getManufacturerById(productData.getManufacturer()));

        List<Image> productDataImages = imageService.getImagesByProductDataId(productDataDomain.getId());
        List<Long> imageIds = new ArrayList<>();

        if (images != null && !images.isEmpty()) {
            images.forEach(image -> {
                if (!Objects.equals(image.getContentType(), "null")) {
                    imageService.saveProductDataImage(image, productDataDomain);
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

        productDataImages.forEach(productDataImage -> {
            if (!imageIds.contains(productDataImage.getId())) {
                imageService.deleteProductDataImage(productDataImage.getId());
            }
        });

        productDataRepository.save(productDataDomain);
    }

    private void saveImage(List<MultipartFile> image, ProductData productDataDomain) {
        if (image != null && !image.isEmpty()) {
            image.forEach(banner -> {
                if (!Objects.equals(banner.getContentType(), "null")) {
                    Image newImage = new Image();
                    String bannerFilename = saveProductDataImage(banner);
                    newImage.setFilename(bannerFilename);
                    newImage.setProductData(productDataDomain);
                    imageService.save(newImage);
                }
            });
        }
    }

    private void saveProductData(ru.fwoods.computerstore.model.ProductData productData, ProductData productDataDomain) {
        productDataDomain.setName(productData.getName());
        productDataDomain.setDescription(productData.getDescription());
        productDataDomain.setCost(productData.getCost());

        Manufacturer manufacturer = manufacturerService.getManufacturerById(productData.getManufacturer());
        Country country = countryService.getCountryById(productData.getCountry());
        ProductCategory category = productCategoryService.getCategoryById(productData.getCategory());

        productDataDomain.setCountry(country);
        productDataDomain.setManufacturer(manufacturer);
        productDataDomain.setCategory(category);
    }

    public ru.fwoods.computerstore.model.ProductData getProductDataModelById(Long id) {
        ProductData productData = productDataRepository.getOne(id);

        ru.fwoods.computerstore.model.ProductData productDataModel = new ru.fwoods.computerstore.model.ProductData();

        productDataModel.setId(productData.getId());
        productDataModel.setName(productData.getName());
        productDataModel.setDescription(productData.getDescription());
        productDataModel.setCost(productData.getCost());
        productDataModel.setManufacturer(productData.getManufacturer().getId());
        productDataModel.setCountry(productData.getCountry().getId());
        productDataModel.setCategory(productData.getCategory().getId());

        return productDataModel;
    }

    public List<ProductData> getProductDataSearch(String searchParam) {
        if (searchParam.length() > 0) return productDataRepository.findAllWithoutPromotionAndSearchParam(new Date(), searchParam);
        else return productDataRepository.findAllWithoutPromotion(new Date());
    }

    public ProductData saveWithAttributes(ProductData productData) {
        return productDataRepository.save(productData);
    }
}
