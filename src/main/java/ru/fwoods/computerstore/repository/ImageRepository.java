package ru.fwoods.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.Image;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image getImageByPromotionId(Long id);

    Image getImageByManufacturerId(Long id);
}
