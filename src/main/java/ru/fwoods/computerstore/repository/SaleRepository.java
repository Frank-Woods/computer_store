package ru.fwoods.computerstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.Sale;
import ru.fwoods.computerstore.model.DateStatistics;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    Page<Sale> getAllByUserId(Long id, Pageable pageable);

    @Query("select s " +
            "from Sale s " +
            "where s.date >= ?1 and s.date <= ?2")
    List<Sale> getSalesByDate(LocalDateTime start, LocalDateTime end);
}
