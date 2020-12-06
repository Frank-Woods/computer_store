package ru.fwoods.computerstore.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.fwoods.computerstore.domain.Sale;
import ru.fwoods.computerstore.model.DateStatistics;
import ru.fwoods.computerstore.model.Statistics;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    Page<Sale> getAllByUserId(Long id, Pageable pageable);

    @Query("select s " +
            "from Sale s " +
            "where s.date >= ?1 and s.date <= ?2")
    List<Sale> getSalesByDate(LocalDateTime start, LocalDateTime end);

    @Query(value =  "select cast(date as date) as date, sum(cost) as sum " +
                    "from sale join sale_product sp " +
                    "on sale.id = sp.sale_id " +
                    "and sale.date >= :start " +
                    "and sale.date <= :end " +
                    "group by cast(date as date)", nativeQuery = true)
    List<Object> getStatistics(@Param("start") LocalDateTime start, @Param("end") LocalDateTime end);
}
