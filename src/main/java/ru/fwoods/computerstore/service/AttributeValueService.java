package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.*;
import ru.fwoods.computerstore.repository.AttributeValueRepository;
import sun.dc.pr.PRError;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AttributeValueService {

    @Autowired
    private AttributeValueRepository attributeValueRepository;

    @Autowired
    private AttributeCategoryService attributeCategoryService;

    @Autowired
    private AttributeService attributeService;

    @Autowired
    private ValueService valueService;

    @Autowired
    private ProductDataService productDataService;

    public AttributeValue save(Attribute attribute, Value value) {
        AttributeValue attributeValueDomain = attributeValueRepository.findByAttributeAndValue(attribute, value);
        if (attributeValueDomain == null) {
            attributeValueDomain = new AttributeValue();
            attributeValueDomain.setAttribute(attribute);
            attributeValueDomain.setValue(value);
            attributeValueDomain = attributeValueRepository.save(attributeValueDomain);
        }
        return attributeValueDomain;
    }

    public List<AttributeValue> getAttributeValueWithOneProduct(Long id) {
        return attributeValueRepository.getAttributeValueWithOneProduct(id);
    }

    public void deleteById(Long id) {
        attributeValueRepository.deleteById(id);
    }

    public Page<AttributeValue> getAttributeValueByProductId(ProductData productData, Pageable pageable) {
        return attributeValueRepository.getAllByProductsContains(productData, pageable);
    }

    public AttributeValue findById(Long id) {
        return attributeValueRepository.getOne(id);
    }

    public void saveAttribute(ru.fwoods.computerstore.model.Attribute attribute, Long id) {
        ProductData productData = productDataService.getProductDataById(id);

        AttributeCategory attributeCategory = attributeCategoryService.save(attribute.getAttributeCategory());
        Attribute attributeDomain = attributeService.save(attribute, attributeCategory);
        Value value = valueService.save(attribute);
        AttributeValue attributeValue = save(attributeDomain, value);

        if (productData.getAttributes() != null) {
            productData.getAttributes().add(attributeValue);
        } else {
            Set<AttributeValue> attributeValueSet = new HashSet<>();
            attributeValueSet.add(attributeValue);
            productData.setAttributes(attributeValueSet);
        }

        productDataService.saveWithAttributes(productData);
    }

    public void update(ru.fwoods.computerstore.model.Attribute attribute) {
        AttributeValue attributeValue = attributeValueRepository.getOne(attribute.getId());

        if (attributeValue.getAttribute().getAttributeValueList().size() == 1 && attributeValue.getValue().getAttributeValueList().size() == 1) {

        } else {

        }
    }

    public void delete(Long id, Long productId) {
        List<AttributeValue> attributeValues = getAttributeValueWithOneProduct(productId);

        AttributeValue attributeValue = attributeValueRepository.getOne(id);

        for (AttributeValue av : attributeValues) {
            if (av.getId().equals(attributeValue.getId())) {
                deleteById(attributeValue.getId());
                attributeService.deleteById(attributeValue.getAttribute().getId());
                valueService.deleteById(attributeValue.getValue());
            }
        }
    }
}
