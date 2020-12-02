package ru.fwoods.computerstore.model;

import ru.fwoods.computerstore.validation.attribute.name.UniqueName;

import javax.validation.constraints.NotEmpty;

@UniqueName
public class Attribute {
    private Long id;

    @NotEmpty(message = "error.validation.attribute.name.empty")
    private String name;

    @NotEmpty(message = "error.validation.attribute.description.empty")
    private String description;

    @NotEmpty(message = "error.validation.attribute.value.empty")
    private String value;

    @NotEmpty(message = "error.validation.attribute.unit.empty")
    private String unit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
