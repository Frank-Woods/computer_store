package ru.fwoods.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {
    City getCityByName(String name);
}
