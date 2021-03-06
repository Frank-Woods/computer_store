package ru.fwoods.computerstore.model;

import ru.fwoods.computerstore.validation.productData.name.UniqueName;

import java.util.List;

@UniqueName
public class ProductData {
    private Long id;

    private String name;

    private String description;

    private Integer cost;

    private Long manufacturer;

    private Long country;

    private Long category;

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

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public Long getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Long manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Long getCountry() {
        return country;
    }

    public void setCountry(Long country) {
        this.country = country;
    }

    public Long getCategory() {
        return category;
    }

    public void setCategory(Long category) {
        this.category = category;
    }
}
