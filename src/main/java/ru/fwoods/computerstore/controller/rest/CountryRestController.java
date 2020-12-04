package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fwoods.computerstore.controller.ControllerUtils;
import ru.fwoods.computerstore.domain.Country;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.service.CountryService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class CountryRestController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private ControllerUtils controllerUtils;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/country/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
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

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/admin/country/get/search")
    public ResponseEntity getCountrySearch(
            @RequestParam(required = false, defaultValue = "") String searchParam
    ) {
        List<Country> counties = countryService.getCountrySearch(searchParam);
        return ResponseEntity.ok().body(counties);
    }
}