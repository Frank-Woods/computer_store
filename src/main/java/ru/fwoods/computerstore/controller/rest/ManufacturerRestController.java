package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.fwoods.computerstore.controller.ControllerUtils;
import ru.fwoods.computerstore.domain.ProductData;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.model.Manufacturer;
import ru.fwoods.computerstore.service.ManufacturerService;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RestController
public class ManufacturerRestController {

    @Autowired
    private ManufacturerService manufacturerService;

    @Autowired
    private ControllerUtils controllerUtils;

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(value = "/admin/manufacturer/get/search")
    public ResponseEntity getManufacturerSearch(
            @RequestParam(required = false, defaultValue = "") String searchParam
    ) {
        List<Manufacturer> manufacturerList = manufacturerService.getManufacturerSearch(searchParam);
        return ResponseEntity.ok().body(manufacturerList);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/manufacturer/create", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity createManufacturer(
            @RequestPart(name = "manufacturer", required = false) @Validated Manufacturer manufacturer,
            @RequestPart(name = "manufacturerLogo", required = false) List<MultipartFile> manufacturerLogo,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        manufacturerService.saveManufacturer(manufacturer, manufacturerLogo);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/manufacturer/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateManufacturer(
            @RequestPart(name = "manufacturer", required = false) @Validated Manufacturer manufacturer,
            @RequestPart(name = "manufacturerLogo", required = false) List<MultipartFile> manufacturerLogo,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        manufacturerService.updateManufacturer(manufacturer, manufacturerLogo);
        return ResponseEntity.ok().body(null);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping(value = "/admin/manufacturer/delete")
    public ResponseEntity<Map<String, String>> deleteManufacturer(@RequestPart(name = "manufacturer") IdWrapper idWrapper) {
        try {
            manufacturerService.deleteManufacturerById(idWrapper.getId());
            return ResponseEntity.ok().body(null);
        }
        catch (Exception ex) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("error", "Удаление невозможно, есть связанные сущности"));
        }
    }
}