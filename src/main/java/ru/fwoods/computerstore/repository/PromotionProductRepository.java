package ru.fwoods.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.PromotionProduct;

@Repository
public interface PromotionProductRepository extends JpaRepository<PromotionProduct, Long> {
}