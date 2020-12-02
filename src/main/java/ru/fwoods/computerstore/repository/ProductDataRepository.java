package ru.fwoods.computerstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.ProductData;

import java.util.List;

@Repository
public interface ProductDataRepository extends JpaRepository<ProductData, Long> {
    Page<ProductData> findAllByNameContainsIgnoreCase(String param, Pageable pageable);

    ProductData getProductDataByName(String name);

    Page<ProductData> findAllByNameContainsIgnoreCaseAndIdNotIn(String param, List<Long> id, Pageable pageable);

    Page<ProductData> getAllByIdNotIn(List<Long> id, Pageable pageable);
}