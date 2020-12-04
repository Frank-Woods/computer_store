package ru.fwoods.computerstore.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {

    @GetMapping("/admin/mail/create")
    public String getCreateMailPage() {
        return "admin/mail/create";
    }
}
