package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.AttributeCategory;
import ru.fwoods.computerstore.domain.ProductCategory;
import ru.fwoods.computerstore.model.Attribute;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.repository.AttributeRepository;

@Service
public class AttributeService {

    @Autowired
    private AttributeRepository attributeRepository;

    @Autowired
    private ProductCategoryService productCategoryService;

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
        attributeDomain.getProductCategories().add(productCategoryService.getCategoryById(id));

        attributeRepository.save(attributeDomain);
    }

    public void update(ru.fwoods.computerstore.model.rest.Attribute attribute) {
        ru.fwoods.computerstore.domain.Attribute attributeDomain = attributeRepository.getOne(attribute.getId());

        attributeDomain.setName(attribute.getName());
        attributeDomain.setDescription(attribute.getDescription());

        attributeRepository.save(attributeDomain);
    }
}
