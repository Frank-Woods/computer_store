package ru.fwoods.computerstore.model;

import ru.fwoods.computerstore.validation.attributeCategory.name.UniqueName;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@UniqueName
public class AttributeCategory {
    private Long id;

    @NotEmpty(message = "error.validation.attributeCategory.name.empty")
    private String name;

    @NotEmpty(message = "error.validation.attributeCategory.attributes.empty")
    private List<Attribute> attributes;

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

    public List<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
    }
}
