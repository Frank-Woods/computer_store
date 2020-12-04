package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.fwoods.computerstore.controller.ControllerUtils;
import ru.fwoods.computerstore.model.IdWrapper;
import ru.fwoods.computerstore.model.User;
import ru.fwoods.computerstore.model.UserPassword;
import ru.fwoods.computerstore.service.UserService;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ControllerUtils controllerUtils;

    @PostMapping(value = "/user/profile/update")
    public ResponseEntity updateUser(
            @RequestPart(name = "user") @Validated User user,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        userService.updateUser(user);
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/add")
    public ResponseEntity addAdmin(
            @RequestPart(name = "user") IdWrapper idWrapper,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        userService.addAdmin(idWrapper.getId());
        return ResponseEntity.ok().body(null);
    }

    @PostMapping(value = "/admin/dismiss")
    public ResponseEntity deleteAdmin(@RequestPart(name = "user") IdWrapper idWrapper) {
        userService.deleteAdmin(idWrapper.getId());
        return ResponseEntity.ok().body(null);
    }
}
