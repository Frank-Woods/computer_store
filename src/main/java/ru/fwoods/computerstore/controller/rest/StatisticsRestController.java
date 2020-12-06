package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import ru.fwoods.computerstore.model.DateStatistics;
import ru.fwoods.computerstore.model.Statistics;
import ru.fwoods.computerstore.service.StatisticsService;

import java.util.List;

@RestController
public class StatisticsRestController {

    @Autowired
    private StatisticsService statisticsService;

    @PostMapping(value = "/statistics/get")
    public List<Statistics> getStatistics(
            @RequestPart(name = "dates") DateStatistics dateStatistics
    ) {
        return statisticsService.getStatistics(dateStatistics);
    }
}
