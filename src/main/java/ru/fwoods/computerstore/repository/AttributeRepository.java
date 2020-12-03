package ru.fwoods.computerstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.Attribute;
import ru.fwoods.computerstore.domain.ProductCategory;

@Repository
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
    Attribute findByName(String Name);

    Attribute getAttributeByName(String name);

    @Query("select a " +
            "from Attribute a join a.category c " +
            "where c.id = ?1")
    Page<Attribute> findByCategory(Long id, Pageable pageable);

    Page<Attribute> getAllByProductCategoriesContains(ProductCategory productCategory, Pageable pageable);
}
