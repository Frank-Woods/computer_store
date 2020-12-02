package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.fwoods.computerstore.domain.Country;
import ru.fwoods.computerstore.domain.Manufacturer;
import ru.fwoods.computerstore.service.CountryService;
import ru.fwoods.computerstore.service.ManufacturerService;

import java.util.Map;

@Controller
public class ManufacturerController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private ManufacturerService manufacturerService;

    @GetMapping("/admin/manufacturers/create")
    public String getManufacturersCreate(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Map<String, Object> model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Country> countriesPage = countryService.getPageCountries(pageable);
        model.put("countriesPage", countriesPage);
        return "manufacturer/create";
    }

//    @PostMapping("/admin/manufacturers/create")
//    public String createManufacturer(
//        ru.fwoods.computerstore.model.Manufacturer manufacturerModel
//    ) {
//        manufacturerService.saveManufacturer(manufacturerModel);
//        return "redirect:/admin/manufacturers";
//    }

    @GetMapping("/admin/manufacturers/update/{id}")
    public String getManufacturersUpdate(
            @PathVariable Long id,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            Model model
    ) {
        Pageable pageable = PageRequest.of(page, 15);
        Page<Country> countriesPage = countryService.getPageCountries(pageable);

        Manufacturer manufacturer = manufacturerService.getManufacturerById(id);

        model.addAttribute("manufacturer", manufacturer);
        model.addAttribute("countriesPage", countriesPage);

        return "manufacturer/update";
    }

//    @PostMapping("/admin/manufacturers/update")
//    public String updateManufacturer(
//        ru.fwoods.computerstore.model.Manufacturer manufacturerModel
//    ) {
//        manufacturerService.saveManufacturer(manufacturerModel);
//        return "redirect:/admin/manufacturers";
//    }

    @PostMapping("/admin/manufacturers/delete")
    public String deleteManufacturer(Long id) {
        manufacturerService.deleteManufacturerById(id);
        return "redirect:/admin/manufacturers";
    }
}
