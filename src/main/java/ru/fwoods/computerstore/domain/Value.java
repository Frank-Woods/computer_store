package ru.fwoods.computerstore.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "value")
public class Value {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "error.validation.value.value.empty")
    @Column(
            name = "value",
            nullable = false
    )
    private String value;

    @NotEmpty(message = "error.validation.value.unit.empty")
    @Column(
            name = "unit",
            nullable = false
    )
    private String unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }
}
