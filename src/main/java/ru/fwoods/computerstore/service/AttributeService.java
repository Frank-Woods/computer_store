package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.AttributeCategory;
import ru.fwoods.computerstore.domain.AttributeValue;
import ru.fwoods.computerstore.domain.ProductCategory;
import ru.fwoods.computerstore.domain.ProductData;
import ru.fwoods.computerstore.model.Attribute;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.repository.AttributeRepository;

import java.util.HashSet;
import java.util.Set;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private AttributeValueService attributeValueService;

    @Autowired
    private ProductDataService productDataService;

    public ru.fwoods.computerstore.domain.Attribute save(Attribute attribute, AttributeCategory attributeCategory) {
        ru.fwoods.computerstore.domain.Attribute attributeDomain = attributeRepository.findByName(attribute.getName());
        if (attributeDomain == null) {
            attributeDomain = new ru.fwoods.computerstore.domain.Attribute();
            attributeDomain.setName(attribute.getName());
            attributeDomain.setDescription(attribute.getDescription());
            attributeDomain.setCategory(attributeCategory);
            attributeDomain = attributeRepository.save(attributeDomain);
        }
        return attributeDomain;
    }

    public ru.fwoods.computerstore.domain.Attribute getAttributeByName(String name) {
        return attributeRepository.getAttributeByName(name);
    }

    public Page<ru.fwoods.computerstore.domain.Attribute> getAllAttributesByAttributeCategory(Pageable pageable, Long attributeCategoryId) {
        return attributeRepository.findByCategory(attributeCategoryId, pageable);
    }

    public ru.fwoods.computerstore.domain.Attribute getAttributeById(Long id) {
        return attributeRepository.getOne(id);
    }

    public void deleteById(Long id) {
        attributeRepository.deleteById(id);
    }

    public Page<ru.fwoods.computerstore.model.rest.Attribute> getAllByProductCategoryId(ProductCategory productCategory, Pageable pageable) {
        Page<ru.fwoods.computerstore.domain.Attribute> attributes = attributeRepository.getAllByProductCategoriesContains(productCategory, pageable);

        return attributes.map(attribute -> {
            ru.fwoods.computerstore.model.rest.Attribute attributeModel = new ru.fwoods.computerstore.model.rest.Attribute();

            attributeModel.setId(attribute.getId());
            attributeModel.setName(attribute.getName());
            attributeModel.setDescription(attribute.getDescription());

            return attributeModel;
        });
    }

    public ru.fwoods.computerstore.model.rest.Attribute getAttributeModelById(Long id) {
        ru.fwoods.computerstore.domain.Attribute attribute = attributeRepository.getOne(id);

        ru.fwoods.computerstore.model.rest.Attribute attributeModel = new ru.fwoods.computerstore.model.rest.Attribute();
        attributeModel.setId(attribute.getId());
        attributeModel.setName(attribute.getName());
        attributeModel.setDescription(attribute.getDescription());

        return attributeModel;
    }

    public void saveAttribute(ru.fwoods.computerstore.model.rest.Attribute attribute, Long id) {
        ru.fwoods.computerstore.domain.Attribute attributeDomain = new ru.fwoods.computerstore.domain.Attribute();

        attributeDomain.setName(attribute.getName());
        attributeDomain.setDescription(attribute.getDescription());

        if (attributeDomain.getProductCategories() != null) {
            attributeDomain.getProductCategories().add(productCategoryService.getCategoryById(id));
        } else {
            Set<ProductCategory> productCategories = new HashSet<>();
            productCategories.add(productCategoryService.getCategoryById(id));
            attributeDomain.setProductCategories(productCategories);
        }
        attributeRepository.save(attributeDomain);
    }

    public void update(ru.fwoods.computerstore.model.rest.Attribute attribute) {
        ru.fwoods.computerstore.domain.Attribute attributeDomain = attributeRepository.getOne(attribute.getId());

        attributeDomain.setName(attribute.getName());
        attributeDomain.setDescription(attribute.getDescription());

        attributeRepository.save(attributeDomain);
    }

    public Page<Attribute> getAllAttributesByProductId(Long id, Pageable pageable) {
        ProductData productData = productDataService.getProductDataById(id);
        Page<AttributeValue> attributeValues = attributeValueService.getAttributeValueByProductId(productData, pageable);

        return attributeValues.map(attributeValue -> {
            Attribute attributeModel = new Attribute();

            attributeModel.setId(attributeValue.getId());
            attributeModel.setName(attributeValue.getAttribute().getName());
            attributeModel.setDescription(attributeValue.getAttribute().getDescription());
            attributeModel.setValue(attributeValue.getValue().getValue());
            attributeModel.setUnit(attributeValue.getValue().getUnit());

            ru.fwoods.computerstore.model.AttributeCategory attributeCategory = new ru.fwoods.computerstore.model.AttributeCategory();
            attributeCategory.setId(attributeValue.getAttribute().getCategory().getId());
            attributeCategory.setName(attributeValue.getAttribute().getCategory().getName());

            attributeModel.setAttributeCategory(attributeCategory);

            return attributeModel;
        });
    }

    public Attribute getAttributeValueModelById(Long id) {
        AttributeValue attributeValue = attributeValueService.findById(id);

        Attribute attribute = new Attribute();

        attribute.setId(attributeValue.getId());
        attribute.setName(attributeValue.getAttribute().getName());
        attribute.setDescription(attributeValue.getAttribute().getDescription());
        attribute.setValue(attributeValue.getValue().getValue());
        attribute.setUnit(attributeValue.getValue().getUnit());

        ru.fwoods.computerstore.model.AttributeCategory attributeCategory = new ru.fwoods.computerstore.model.AttributeCategory();
        attributeCategory.setId(attributeValue.getAttribute().getCategory().getId());
        attributeCategory.setName(attributeValue.getAttribute().getCategory().getName());

        attribute.setAttributeCategory(attributeCategory);

        return attribute;
    }
}
