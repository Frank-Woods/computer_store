package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.Email;
import ru.fwoods.computerstore.domain.User;
import ru.fwoods.computerstore.repository.EmailRepository;

import java.util.List;

@Service
public class EmailService {

    @Autowired
    private EmailRepository emailRepository;

    @Autowired
    private UserService userService;

    public void saveEmail(String email) {
        Email emailOld = emailRepository.getByEmail(email);
        User user = userService.getUserByEmail(email);
        if (user == null && emailOld == null) {
            Email emailDomain = new Email();
            emailDomain.setEmail(email);
            emailRepository.save(emailDomain);
        }
    }

    public List<Email> getAll() {
        return emailRepository.findAll();
    }

    public void delete(Email email) {
        emailRepository.delete(email);
    }
}