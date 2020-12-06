package ru.fwoods.computerstore.model;

import java.time.LocalDateTime;
import java.util.Date;

public class Statistics {

    LocalDateTime date;
    
    Long sum;

    public Statistics(LocalDateTime date, Long sum) {
        this.date = date;
        this.sum = sum;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getSum() {
        return sum;
    }

    public void setSum(Long sum) {
        this.sum = sum;
    }
}
