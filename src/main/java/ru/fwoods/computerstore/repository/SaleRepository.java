package ru.fwoods.computerstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    Page<Sale> getAllByUserId(Long id, Pageable pageable);
}
