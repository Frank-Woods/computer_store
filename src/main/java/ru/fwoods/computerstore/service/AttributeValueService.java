package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.Attribute;
import ru.fwoods.computerstore.domain.AttributeValue;
import ru.fwoods.computerstore.domain.Value;
import ru.fwoods.computerstore.repository.AttributeValueRepository;

import java.util.List;

@Service
public class AttributeValueService {
    @Autowired
    private AttributeValueRepository attributeValueRepository;

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
}
