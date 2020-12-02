package ru.fwoods.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.Value;

@Repository
public interface ValueRepository extends JpaRepository<Value, Long> {
    Value findByValue(String value);
}
