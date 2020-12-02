package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.Image;
import ru.fwoods.computerstore.repository.ImageRepository;

@Service
public class ImageService {
    @Autowired
    private ImageRepository imageRepository;

    public String getImageByPromotionId(Long id) {
        return imageRepository.getImageByPromotionId(id).getFilename();
    }

    public String getImageByManufacturerId(Long id) {
        return imageRepository.getImageByManufacturerId(id).getFilename();
    }

    public void save(Image logo) {
        imageRepository.save(logo);
    }
}
