package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fwoods.computerstore.domain.Country;
import ru.fwoods.computerstore.domain.Manufacturer;
import ru.fwoods.computerstore.service.CountryService;
import ru.fwoods.computerstore.service.ImageService;
import ru.fwoods.computerstore.service.ManufacturerService;

import java.util.List;
import java.util.Map;

@Controller
public class ManufacturerController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ImageService imageService;

    @GetMapping("/admin/manufacturer/create")
    public String getManufacturersCreate() {
        return "admin/manufacturer/create";
    }

    @GetMapping("/admin/manufacturer/update/{id}")
    public String getManufacturersUpdate(
            @PathVariable Long id,
            Model model
    ) {
        Manufacturer manufacturer = manufacturerService.getManufacturerById(id);
        String logo = imageService.getImageByManufacturerId(id);

        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("logo", logo);

        return "admin/manufacturer/update";
    }
}
