package ru.fwoods.computerstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.AttributeCategory;

import java.util.List;

@Repository
public interface AttributeCategoryRepository extends JpaRepository<AttributeCategory, Long> {
    AttributeCategory findByName(String Name);

    AttributeCategory getAttributeCategoryByName(String name);

    Page<AttributeCategory> findAllByIdNotIn(List<Long> id, Pageable pageable);

    @Query("select ac.id, ac.name " +
            "from AttributeCategory ac join Attribute a join AttributeValue av join ProductData pd " +
            "where pd.id = ?1")
    List<ru.fwoods.computerstore.model.AttributeCategory> getUseAttributeCategories(Long productData);
}
