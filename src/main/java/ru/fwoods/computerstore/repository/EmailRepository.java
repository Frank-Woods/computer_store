package ru.fwoods.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.fwoods.computerstore.domain.Email;

public interface EmailRepository extends JpaRepository<Email, Long> {

    Email getByEmail(String email);
}
