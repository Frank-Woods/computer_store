package ru.fwoods.computerstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.ProductCategory;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
    List<ProductCategory> getCategoriesByIdNot(Long id);

    List<ProductCategory> getCategoriesByParentId(Long id);

    List<ProductCategory> getCategoriesByParentNull();

    List<ProductCategory> getCategoriesByProductsNull();

    List<ProductCategory> getCategoryByParentIdInAndIdNot(Collection<Long> parent_id, Long id);

    List<ProductCategory> getProductCategoriesByIdIn(List<Long> id);

    ProductCategory getProductCategoryByName(String name);

    List<ProductCategory> getProductCategoriesByChildrenNull();

    List<ProductCategory> findAllByNameContainsIgnoreCase(String searchParam);
}
