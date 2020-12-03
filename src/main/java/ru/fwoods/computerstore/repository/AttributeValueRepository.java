package ru.fwoods.computerstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.Attribute;
import ru.fwoods.computerstore.domain.AttributeValue;
import ru.fwoods.computerstore.domain.ProductData;
import ru.fwoods.computerstore.domain.Value;

import java.util.List;

@Repository
public interface AttributeValueRepository extends JpaRepository<AttributeValue, Long> {
    AttributeValue findByAttributeAndValue(Attribute attribute, Value value);

    @Query(value = "select pTmp.* " +
            "from product_attribute pa join " +
                "(select av.* " +
                "from product_attribute pa join attribute_value av " +
                "on pa.attribute_value_id = av.id " +
                "and pa.product_data_id = ?1) as pTmp " +
            "on pa.attribute_value_id = pTmp.id " +
            "and pa.product_data_id != ?1", nativeQuery = true)
    List<AttributeValue> getAttributeValueWithOneProduct(Long id);

    Page<AttributeValue> getAllByProductsContains(ProductData productData, Pageable pageable);
}
