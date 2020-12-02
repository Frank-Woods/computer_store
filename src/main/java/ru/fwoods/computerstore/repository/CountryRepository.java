package ru.fwoods.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
    Country getCountryByName(String name);
}
