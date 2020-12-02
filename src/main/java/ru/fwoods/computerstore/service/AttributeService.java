package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.AttributeCategory;
import ru.fwoods.computerstore.model.Attribute;
import ru.fwoods.computerstore.repository.AttributeRepository;

@Service
public class AttributeService {
    @Autowired
    private AttributeRepository attributeRepository;

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

    public void deleteById(ru.fwoods.computerstore.domain.Attribute attribute) {
        attributeRepository.deleteById(attribute.getId());
    }
}
