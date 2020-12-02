package ru.fwoods.computerstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.fwoods.computerstore.domain.Country;
import ru.fwoods.computerstore.service.CountryService;

@Controller
public class CountryController {
    @Autowired
    private CountryService countryService;

    @GetMapping("/admin/country/create")
    public String getCountriesCreate() {
        return "admin/country/create";
    }

    @PostMapping("/admin/country/create")
    public String createCountry(Country country) {
        countryService.saveCountry(country);
        return "redirect:/admin/countries";
    }

    @GetMapping("/admin/countries/update/{id}")
    public String getCountriesUpdate(
            @PathVariable Long id,
            Model model
    ) {
        Country country = countryService.getCountryById(id);
        model.addAttribute("country", country);
        return "country/update";
    }

    @PostMapping("/admin/countries/update")
    public String updateCountry(Country country) {
        countryService.saveCountry(country);
        return "redirect:/admin/countries";
    }

    @PostMapping("/admin/countries/delete")
    public String deleteCountry(Long id) {
        countryService.deleteCountryById(id);
        return "redirect:/admin/countries";
    }
}
