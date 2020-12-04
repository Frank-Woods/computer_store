package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import ru.fwoods.computerstore.domain.Email;
import ru.fwoods.computerstore.domain.User;
import ru.fwoods.computerstore.model.MailMessage;
import ru.fwoods.computerstore.service.EmailService;
import ru.fwoods.computerstore.service.MailSender;
import ru.fwoods.computerstore.service.UserService;

import java.util.List;

@RestController
public class MailRestController {

    @Autowired
    private MailSender mailSender;

    @Autowired
    private UserService userService;

    @Autowired
    private EmailService emailService;

    @PostMapping("/admin/mail/send")
    public ResponseEntity sendMail(@RequestPart("mail") MailMessage mailMessage) {

        List<User> users = userService.getAllOnlyUser();

        List<Email> emails = emailService.getAll();

        mailMessage.setBody("<td colspan='3'>" + mailMessage.getBody() + "</td>");

        users.forEach(user -> {
            mailSender.send(user.getEmail(), mailMessage.getTitle(), mailMessage);
        });

        emails.forEach(email -> {
            mailSender.send(email.getEmail(), mailMessage.getTitle(), mailMessage);
        });

        return ResponseEntity.ok().body(null);
    }
}
