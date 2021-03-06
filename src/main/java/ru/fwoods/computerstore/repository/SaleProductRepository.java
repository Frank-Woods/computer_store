package ru.fwoods.computerstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.SaleProduct;

import java.util.List;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProduct,Long> {

    Page<SaleProduct> getAllBySaleId(Long id, Pageable pageable);
}
