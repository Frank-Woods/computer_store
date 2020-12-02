package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import ru.fwoods.computerstore.controller.ControllerUtils;
import ru.fwoods.computerstore.domain.Country;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.service.CountryService;

import java.util.Collections;
import java.util.Map;

@RestController
public class CountryRestController {
    @Autowired
    private CountryService countryService;

    @Autowired
    private ControllerUtils controllerUtils;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/country/create")
    public ResponseEntity createCountry(
            @RequestPart(name = "country", required = false) @Validated Country country,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        countryService.saveCountry(country);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/country/update")
    public ResponseEntity updateCountry(
            @RequestPart(name = "country", required = false) @Validated Country country,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        countryService.saveCountry(country);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/country/delete")
    public ResponseEntity<Map<String, String>> deleteCountry(@RequestPart(name = "country") IdWrapper idWrapper) {
        try {
            countryService.deleteCountryById(idWrapper.getId());
            return ResponseEntity.ok().body(null);
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Удаление невозможно, есть связанные сущности"));
        }
    }
}