package ru.fwoods.computerstore.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity getStatistics(
            @RequestPart(name = "dates") DateStatistics dateStatistics
    ) {
        return ResponseEntity.ok().body(statisticsService.getStatistics(dateStatistics));
    }
}
