package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import ru.fwoods.computerstore.model.Attribute;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.service.AttributeValueService;

@RestController
public class AttributeRestController {

    @Autowired
    private AttributeValueService attributeValueService;

    @PostMapping(value = "/admin/product/{id}/attribute/create")
    public ResponseEntity createAttribute(
            @PathVariable Long id,
            @RequestPart(name = "attribute") Attribute attribute
    ) {
        attributeValueService.saveAttribute(attribute, id);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/product/attribute/update")
    public ResponseEntity updateAttribute(
            @RequestPart(name = "attribute") Attribute attribute
    ) {
        attributeValueService.update(attribute);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/product/{id}/attribute/delete")
    public ResponseEntity deleteAttribute(
            @PathVariable Long id,
            @RequestPart(name = "attribute") IdWrapper idWrapper
    ) {
        attributeValueService.delete(idWrapper.getId(), id);
        return ResponseEntity.ok().body(null);
    }
}
