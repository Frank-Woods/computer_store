package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.AttributeCategory;
import ru.fwoods.computerstore.repository.AttributeCategoryRepository;

import java.util.List;

@Service
public class AttributeCategoryService {
    @Autowired
    private AttributeCategoryRepository attributeCategoryRepository;

    public AttributeCategory save(String attributeCategory) {
        AttributeCategory attributeCategoryDomain = attributeCategoryRepository.findByName(attributeCategory);
        if (attributeCategoryDomain == null) {
            attributeCategoryDomain = new AttributeCategory();
            attributeCategoryDomain.setName(attributeCategory);
            attributeCategoryDomain = attributeCategoryRepository.save(attributeCategoryDomain);
        }
        return attributeCategoryDomain;
    }

    public AttributeCategory getAttributeCategoryByName(String name) {
        return attributeCategoryRepository.getAttributeCategoryByName(name);
    }

    public Page<AttributeCategory> getAllAttributeCategoryWithoutExcluded(Pageable pageable, List<Long> attributeCategories) {
        if (attributeCategories == null) return  attributeCategoryRepository.findAll(pageable);
        return attributeCategoryRepository.findAllByIdNotIn(attributeCategories, pageable);
    }

    public List<ru.fwoods.computerstore.model.AttributeCategory> getUseAttributeCategories(Long id) {
        return attributeCategoryRepository.getUseAttributeCategories(id);
    }

    public List<AttributeCategory> getAttributeCategoriesByProductDataId(Long id) {
        return attributeCategoryRepository.getAttributeCategoriesByProductDataId(id);
    }
}
