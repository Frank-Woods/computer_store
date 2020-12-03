package ru.fwoods.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.Image;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    Image getImageByPromotionId(Long id);

    Image getImageByManufacturerId(Long id);

    List<Image> getImagesByProductDataId(Long id);

    List<Image> getImagesByPromotionId(Long id);

    List<Image> getImagesByManufacturerId(Long id);
}
