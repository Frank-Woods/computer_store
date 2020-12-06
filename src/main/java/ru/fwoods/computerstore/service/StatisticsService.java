package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.Sale;
import ru.fwoods.computerstore.domain.SaleProduct;
import ru.fwoods.computerstore.model.DateStatistics;
import ru.fwoods.computerstore.model.Statistics;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private SaleService saleService;

    public List<Statistics> getStatistics(DateStatistics dateStatistics) {
        List<Sale> sales = saleService.getSalesByDate(dateStatistics);

        List<Statistics> statistics = new ArrayList<>();

        for (LocalDateTime i = dateStatistics.getStart(); i.equals(dateStatistics.getEnd()); i = i.plusDays(1)) {
            statistics.add(new Statistics(i, 0L));
        }

        for (Sale sale : sales) {
            for (int i = 1; i < statistics.size(); i++) {
                if (sale.getDate().isAfter(statistics.get(i-1).getDate()) && sale.getDate().isBefore(statistics.get(i).getDate())) {
                    Long plus = statistics.get(i-1).getSum();
                    for (SaleProduct saleProduct : sale.getSaleProductList()) {
                        plus += saleProduct.getCost();
                    }
                    statistics.get(i-1).setSum(plus);
                }
            }
        }

        return statistics;
    }
}
