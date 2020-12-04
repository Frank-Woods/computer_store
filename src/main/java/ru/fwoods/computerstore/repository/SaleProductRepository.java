package ru.fwoods.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.SaleProduct;

import java.util.List;

@Repository
public interface SaleProductRepository extends JpaRepository<SaleProduct,Long> {

    List<SaleProduct> getAllBySaleId(Long id);
}
