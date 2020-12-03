package ru.fwoods.computerstore.model;

import ru.fwoods.computerstore.validation.productCategory.name.UniqueName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

@UniqueName
public class ProductCategory {
    private Long id;

    @NotEmpty(message = "error.validation.productCategory.name.empty")
    private String name;

    @NotEmpty(message = "error.validation.productCategory.description.empty")
    private String description;

    private Long parent;

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

    public Long getParent() {
        return parent;
    }

    public void setParent(Long parent) {
        this.parent = parent;
    }
}
