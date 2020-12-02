package ru.fwoods.computerstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.Manufacturer;

@Repository
public interface ManufacturerRepository extends JpaRepository<Manufacturer, Long> {
    Manufacturer getManufacturerByName(String name);
}
