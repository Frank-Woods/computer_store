package ru.fwoods.computerstore.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MailController {

    @GetMapping("/admin/mail/create")
    public String getCreateMailPage() {
        return "admin/mailing/create";
    }
}
