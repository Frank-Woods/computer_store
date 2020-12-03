package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.Attribute;
import ru.fwoods.computerstore.domain.ProductCategory;
import ru.fwoods.computerstore.model.Category;
import ru.fwoods.computerstore.repository.ProductCategoryRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ProductCategoryService {
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Autowired
    private AttributeService attributeService;

    public ProductCategory saveCategory(ProductCategory productCategory) {
        return productCategoryRepository.save(productCategory);
    }

    public List<ProductCategory> getAllCategories() {
        return productCategoryRepository.findAll();
    }

    public ProductCategory getCategoryById(Long id) {
        return productCategoryRepository.findById(id).orElse(null);
    }

    public void deleteCategoryById(Long id) {
        ProductCategory productCategory = getCategoryById(id);
        List<ProductCategory> childCategories = getChildCategories(productCategory.getId());
        if (productCategory.getParent() == null) {
            childCategories.forEach(childCategory -> childCategory.setParent(null));
        } else {
            childCategories.forEach(childCategory -> childCategory.setParent(productCategory.getParent()));
        }
        productCategoryRepository.deleteById(id);
    }

    public List<ProductCategory> getCategoriesByIdNot(Long id) {
        List<ProductCategory> categories = productCategoryRepository.getCategoriesByIdNot(id);
        return categories;
    }

    public List<ProductCategory> getChildCategories(Long id) {
        return productCategoryRepository.getCategoriesByParentId(id);
    }

    public List<ProductCategory> getCategoriesWithoutParent() {
        return productCategoryRepository.getCategoriesByParentNull();
    }

    public void updateChildCategories(List<ProductCategory> children, ProductCategory parent) {
        children.stream()
                .filter(Objects::nonNull)
                .forEach(category -> {
                    category.setParent(parent);
                    saveCategory(category);
                });
    }

    public void updateCategory(ru.fwoods.computerstore.model.ProductCategory productCategory) {
        ProductCategory productCategoryDomain = productCategoryRepository.getOne(productCategory.getId());

        productCategoryDomain.setName(productCategory.getName());
        productCategoryDomain.setDescription(productCategory.getDescription());
        if (productCategory.getParent() != null) {
            productCategoryDomain.setParent(productCategoryRepository.getOne(productCategory.getParent()));
        }

        productCategoryRepository.save(productCategoryDomain);

    }

    public List<ProductCategory> getNeighboringAndChildCategories(ProductCategory productCategory) {
        if (productCategory.getParent() == null) {
            return productCategoryRepository.getCategoriesByParentId(productCategory.getId());
        } else {
            return productCategoryRepository.getCategoryByParentIdInAndIdNot(Arrays.asList(productCategory.getId(), productCategory.getParent().getId()), productCategory.getId());
        }
    }

    public Page<ProductCategory> getPageCategories(Pageable pageable) {
        return  productCategoryRepository.findAll(pageable);
    }

    public List<Category> getAllCategoriesModel() {
        List<ProductCategory> categories = productCategoryRepository.getCategoriesByParentNull();
        return categories.stream().map(category -> {
            Category categoryModel = new Category();
            categoryModel.setId(category.getId());
            categoryModel.setName(category.getName());
            categoryModel.setConvertedChildren(category.getChildren());
            return categoryModel;
        }).collect(Collectors.toList());
    }

    public void saveProductCategory(ru.fwoods.computerstore.model.ProductCategory productCategory) {
        ProductCategory productCategoryDomain = new ProductCategory();

        productCategoryDomain.setName(productCategory.getName());
        productCategoryDomain.setDescription(productCategory.getDescription());
        if (productCategory.getParent() != null) {
            productCategoryDomain.setParent(productCategoryRepository.getOne(productCategory.getParent()));
        }

        productCategoryRepository.save(productCategoryDomain);
    }

    public void updateProductCategoryParent(Category category, ProductCategory categorySaved) {
        ProductCategory productCategory = productCategoryRepository.getOne(category.getId());
        if (category.getParent() == null) {
            productCategory.setParent(null);
        } else if (category.getParent() == -1) {
            productCategory.setParent(categorySaved);
        } else {
            ProductCategory categoryParent = productCategoryRepository.getOne(category.getParent());
            productCategory.setParent(categoryParent);
        }
        productCategoryRepository.save(productCategory);
    }

    public ProductCategory getProductCategoryByName(String name) {
        return productCategoryRepository.getProductCategoryByName(name);
    }

    public Page<Category> getCategoriesWithoutChildren(Pageable pageable) {
        Page<ProductCategory> productCategories = productCategoryRepository.getProductCategoriesByChildrenNull(pageable);
        return productCategories.map(productCategory -> {
            Category category = new Category();
            category.setId(productCategory.getId());
            category.setName(productCategory.getName());
            return category;
        });
    }

    public List<ru.fwoods.computerstore.model.ProductCategory> getCategoriesWithoutParentAndProduct() {
        List<ProductCategory> productCategories = productCategoryRepository.getCategoriesByParentNullAndProductsNull();

        return productCategories.stream().map(productCategory -> {
            ru.fwoods.computerstore.model.ProductCategory productCategoryModel = new ru.fwoods.computerstore.model.ProductCategory();

            productCategoryModel.setId(productCategory.getId());
            productCategoryModel.setName(productCategory.getName());

            return productCategoryModel;
        }).collect(Collectors.toList());
    }

    public List<ru.fwoods.computerstore.model.ProductCategory> getProductCategorySearch(String searchParam) {
        List<ProductCategory> productCategories;
        if (searchParam.length() > 0) productCategories = productCategoryRepository.findAllByNameContainsIgnoreCase(searchParam);
        else productCategories = productCategoryRepository.findAll();

        return productCategories.stream().map(productCategory -> {
            ru.fwoods.computerstore.model.ProductCategory productCategoryModel = new ru.fwoods.computerstore.model.ProductCategory();

            productCategoryModel.setId(productCategory.getId());
            productCategoryModel.setName(productCategory.getName());
            productCategoryModel.setDescription(productCategory.getDescription());

            return productCategoryModel;
        }).collect(Collectors.toList());
    }
}
