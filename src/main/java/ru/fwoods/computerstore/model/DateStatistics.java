package ru.fwoods.computerstore.model;

import java.sql.Date;
import java.time.LocalDateTime;

public class DateStatistics {

    LocalDateTime start;
    LocalDateTime end;

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public LocalDateTime getEnd() {
        return end;
    }

    public void setEnd(LocalDateTime end) {
        this.end = end;
    }
}
