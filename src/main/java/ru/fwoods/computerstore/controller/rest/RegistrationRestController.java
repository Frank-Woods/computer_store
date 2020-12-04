package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import ru.fwoods.computerstore.controller.ControllerUtils;
import ru.fwoods.computerstore.model.User;
import ru.fwoods.computerstore.service.RegistrationService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
public class RegistrationRestController {

    @Autowired
    private RegistrationService registrationService;

    @Autowired
    private ControllerUtils controllerUtils;

    @PostMapping(value = "/user/registration", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ModelAndView saveUser(
            @RequestPart(name = "user") User user,
            HttpServletRequest httpServletRequest,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = controllerUtils.getErrors(bindingResult);
            return new ModelAndView("redirect:/user/registration", errors);
        }
        ru.fwoods.computerstore.domain.User userDomain = registrationService.save(user);
        httpServletRequest.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        httpServletRequest.setAttribute("user", userDomain);
        return new ModelAndView("redirect:/login");
    }
}
