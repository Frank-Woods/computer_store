package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.computerstore.domain.Image;
import ru.fwoods.computerstore.domain.Manufacturer;
import ru.fwoods.computerstore.domain.ProductData;
import ru.fwoods.computerstore.repository.CountryRepository;
import ru.fwoods.computerstore.repository.ManufacturerRepository;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ManufacturerService {

    @Autowired
    private FileService fileService;

    @Autowired
    private ManufacturerRepository manufacturerRepository;

    @Autowired
    private CountryRepository countryRepository;

    @Autowired
    private ImageService imageService;

    @Autowired
    private ProductDataService productDataService;

    @Value("${manufacturer.upload.path}")
    private String manufacturerUploadPath;

    public Manufacturer getManufacturerById(Long id) {
        return manufacturerRepository.findById(id).orElse(null);
    }

    public void saveManufacturer(ru.fwoods.computerstore.model.Manufacturer manufacturer, List<MultipartFile> images) {
        Manufacturer manufacturerDomain = new Manufacturer();

        manufacturerDomain.setName(manufacturer.getName());
        manufacturerDomain.setDescription(manufacturer.getDescription());
        manufacturerDomain.setCountry(countryRepository.getOne(manufacturer.getCountry()));

        Manufacturer manufacturerDomainSaved = manufacturerRepository.save(manufacturerDomain);

        if (images != null && !images.isEmpty()) {
            images.forEach(image -> imageService.saveManufacturerImage(image, manufacturerDomainSaved));
        }
    }

    public void deleteManufacturerById(Long id) {
        Manufacturer manufacturerDomain = manufacturerRepository.getOne(id);
        List<Image> manufacturerImages = imageService.getImagesByManufacturerId(manufacturerDomain.getId());

        manufacturerImages.forEach(manufacturerImage -> {
            imageService.deleteManufacturerImage(manufacturerImage.getId());
        });

        manufacturerRepository.deleteById(id);
    }

    public Page<Manufacturer> getPageManufacturers(Pageable pageable) {
        return manufacturerRepository.findAll(pageable);
    }

    public Manufacturer getManufacturerByName(String name) {
        return  manufacturerRepository.getManufacturerByName(name);
    }

    public void updateManufacturer(ru.fwoods.computerstore.model.Manufacturer manufacturer, List<MultipartFile> images) {
        Manufacturer manufacturerDomain = manufacturerRepository.getOne(manufacturer.getId());

        manufacturerDomain.setName(manufacturer.getName());
        manufacturerDomain.setDescription(manufacturer.getDescription());
        manufacturerDomain.setCountry(countryRepository.getOne(manufacturer.getCountry()));

        List<Image> manufacturerImages = imageService.getImagesByManufacturerId(manufacturerDomain.getId());
        List<Long> imageIds = new ArrayList<>();

        if (images != null && !images.isEmpty()) {
            images.forEach(image -> {
                if (!Objects.equals(image.getContentType(), "null")) {
                    imageService.saveManufacturerImage(image, manufacturerDomain);
                } else {
                    try {
                        Long id = Long.parseLong(image.getOriginalFilename());
                        imageIds.add(id);
                    } catch (NullPointerException | NumberFormatException exception) {
                        exception.printStackTrace();
                    }
                }
            });
        }

        manufacturerImages.forEach(manufacturerImage -> {
            if (!imageIds.contains(manufacturerImage.getId())) {
                imageService.deleteManufacturerImage(manufacturerImage.getId());
            }
        });

        manufacturerRepository.save(manufacturerDomain);
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public List<ru.fwoods.computerstore.model.Manufacturer> getManufacturerSearch(String searchParam) {
        List<Manufacturer> manufacturers;
        if (searchParam.length() > 0) manufacturers = manufacturerRepository.findAllByNameContainsIgnoreCase( searchParam);
        else manufacturers = manufacturerRepository.findAll();

        return manufacturers.stream().map(manufacturer -> {
            ru.fwoods.computerstore.model.Manufacturer manufacturerModel = new ru.fwoods.computerstore.model.Manufacturer();

            manufacturerModel.setId(manufacturer.getId());
            manufacturerModel.setName(manufacturer.getName());
            manufacturerModel.setDescription(manufacturer.getDescription());
            manufacturerModel.setCountry(manufacturer.getCountry().getId());

            return manufacturerModel;
        }).collect(Collectors.toList());
    }

    public Set<Manufacturer> getManufacturerInCategory(Long category) {
        List<ProductData> productDataList = productDataService.getAllByCategoryId(category);
        Set<Manufacturer> manufacturers = new HashSet<>();
        productDataList.forEach(productData -> {
            manufacturers.add(productData.getManufacturer());
        });

        return manufacturers;
    }
}
