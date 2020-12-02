package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.computerstore.domain.Image;
import ru.fwoods.computerstore.domain.Manufacturer;
import ru.fwoods.computerstore.repository.CountryRepository;
import ru.fwoods.computerstore.repository.ManufacturerRepository;

import java.util.List;

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

    @Value("${manufacturer.upload.path}")
    private String manufacturerUploadPath;

    public Manufacturer getManufacturerById(Long id) {
        return manufacturerRepository.findById(id).orElse(null);
    }

    private String saveManufacturerLogo(MultipartFile file) {
        return fileService.createFile(manufacturerUploadPath, file);
    }

    public void saveManufacturer(ru.fwoods.computerstore.model.Manufacturer manufacturer, List<MultipartFile> manufacturerLogo) {
        Manufacturer manufacturerDomain = new Manufacturer();

        save(manufacturer, manufacturerLogo, manufacturerDomain);
    }

    private void save(ru.fwoods.computerstore.model.Manufacturer manufacturer, List<MultipartFile> manufacturerLogo, Manufacturer manufacturerDomain) {
        manufacturerDomain.setName(manufacturer.getName());
        manufacturerDomain.setDescription(manufacturer.getDescription());
        manufacturerDomain.setCountry(countryRepository.getOne(manufacturer.getCountry()));

        Manufacturer manufacturerDomainSaved = manufacturerRepository.save(manufacturerDomain);

        if (manufacturerLogo != null && !manufacturerLogo.isEmpty()) {
            Image logo = new Image();
            String logoFilename = saveManufacturerLogo(manufacturerLogo.get(0));
            logo.setFilename(logoFilename);
            logo.setManufacturer(manufacturerDomainSaved);
            imageService.save(logo);
            manufacturerDomain.setLogo(logo);
        }

        manufacturerRepository.save(manufacturerDomain);
    }

    public List<Manufacturer> getAllManufacturers() {
        return manufacturerRepository.findAll();
    }

    public void deleteManufacturerById(Long id) {
        manufacturerRepository.deleteById(id);
    }

    public Page<Manufacturer> getPageManufacturers(Pageable pageable) {
        return manufacturerRepository.findAll(pageable);
    }

    public Manufacturer getManufacturerByName(String name) {
        return  manufacturerRepository.getManufacturerByName(name);
    }

    public void updateManufacturer(ru.fwoods.computerstore.model.Manufacturer manufacturer, List<MultipartFile> manufacturerLogo) {
        Manufacturer manufacturerDomain = manufacturerRepository.getOne(manufacturer.getId());

        save(manufacturer, manufacturerLogo, manufacturerDomain);
    }
}
