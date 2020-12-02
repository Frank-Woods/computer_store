package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.computerstore.domain.*;
import ru.fwoods.computerstore.repository.ProductDataRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

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

    public void save(ru.fwoods.computerstore.model.ProductData productData, List<MultipartFile> image) {
        Set<AttributeValue> attributes = new HashSet<>();

        ProductData productDataDomain = new ProductData();

        productDataDomain.setId(productData.getId());
        saveProductData(productData, productDataDomain);

        ProductData productDataDomainSaved = productDataRepository.save(productDataDomain);

        saveImage(image, productDataDomainSaved);

        saveAttributeValue(productData, attributes, productDataDomain);
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

    public void update(ru.fwoods.computerstore.model.ProductData productData, List<MultipartFile> image) {
        Set<AttributeValue> attributes = new HashSet<>();

        ProductData productDataDomain = productDataRepository.getOne(productData.getId());

        saveProductData(productData, productDataDomain);

        saveImage(image, productDataDomain);

        List<AttributeValue> attributeValues = attributeValueService.getAttributeValueWithOneProduct(productData.getId());

        attributeValues.forEach(attributeValue -> {
            attributeService.deleteById(attributeValue.getAttribute());
            valueService.deleteById(attributeValue.getValue());
            attributeValueService.deleteById(attributeValue.getId());
        });

        saveAttributeValue(productData, attributes, productDataDomain);
    }

    private void saveAttributeValue(ru.fwoods.computerstore.model.ProductData productData, Set<AttributeValue> attributes, ProductData productDataDomain) {
        productData.getAttributeCategories().forEach(attributeCategory -> {
            AttributeCategory attributeCategoryDomain = attributeCategoryService.save(attributeCategory);
            attributeCategory.getAttributes().forEach(attribute -> {
                Attribute attributeDomain = attributeService.save(attribute, attributeCategoryDomain);
                Value valueDomain = valueService.save(attribute);
                AttributeValue attributeValueDomain = attributeValueService.save(attributeDomain, valueDomain);
                attributes.add(attributeValueDomain);
            });
        });

        productDataDomain.setAttributes(attributes);

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
        productDataDomain.setShelfLife(productData.getShelfLife());

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
        productDataModel.setShelfLife(productData.getShelfLife());
        productDataModel.setManufacturer(productData.getManufacturer().getId());
        productDataModel.setCountry(productData.getCountry().getId());
        productDataModel.setCategory(productData.getCategory().getId());

        List<ru.fwoods.computerstore.model.AttributeCategory> attributeCategories = attributeCategoryService.getUseAttributeCategories(productData.getId());

        productData.getAttributes().forEach(attributeValue -> {
            ru.fwoods.computerstore.model.Attribute attribute = new ru.fwoods.computerstore.model.Attribute();
            attribute.setId(attributeValue.getId());
            attribute.setName(attributeValue.getAttribute().getName());
            attribute.setDescription(attributeValue.getAttribute().getDescription());
            attribute.setValue(attributeValue.getValue().getValue());
            attribute.setUnit(attributeValue.getValue().getUnit());
            attributeCategories.forEach(attributeCategory -> {
                if (attributeCategory.getId().equals(attributeValue.getAttribute().getCategory().getId())) {
                    attributeCategory.getAttributes().add(attribute);
                }
            });
        });

        return productDataModel;
    }
}