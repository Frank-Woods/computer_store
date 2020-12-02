package ru.fwoods.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.Promotion;

@Repository
public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion getPromotionByName(String name);
}
