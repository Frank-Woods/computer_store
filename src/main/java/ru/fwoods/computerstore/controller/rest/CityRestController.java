package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fwoods.computerstore.controller.ControllerUtils;
import ru.fwoods.computerstore.domain.Country;
import ru.fwoods.computerstore.model.City;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.service.CityService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class CityRestController {

    @Autowired
    private CityService cityService;

    @Autowired
    private ControllerUtils controllerUtils;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/city/create")
    public ResponseEntity createCity(
            @RequestPart(name = "city", required = false) @Validated City city,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        cityService.save(city);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/city/update")
    public ResponseEntity updateCity(
            @RequestPart(name = "city", required = false) @Validated City city,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        cityService.update(city);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/city/delete")
    public ResponseEntity<Map<String, String>> deleteCity(@RequestPart(name = "city") IdWrapper idWrapper) {
        try {
            cityService.deleteCity(idWrapper.getId());
            return ResponseEntity.ok().body(null);
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Удаление невозможно, есть связанные сущности"));
        }
    }

    @GetMapping(value = "/admin/city/get/search")
    public ResponseEntity getCountrySearch(
            @RequestParam(required = false, defaultValue = "") String searchParam
    ) {
        List<ru.fwoods.computerstore.domain.City> city = cityService.getCitySearch(searchParam);
        return ResponseEntity.ok().body(city);
    }
}