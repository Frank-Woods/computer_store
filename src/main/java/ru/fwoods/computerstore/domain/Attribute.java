package ru.fwoods.computerstore.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "attribute")
public class Attribute {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "error.validation.attribute.name.empty")
    @Column(
            name = "name",
            length = 50,
            nullable = false
    )
    private String name;

    @NotEmpty(message = "error.validation.attribute.description.empty")
    @Column(
            name = "description",
            length = 150
    )
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "attribute_category_id", nullable = false)
    private AttributeCategory category;

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

    public AttributeCategory getCategory() {
        return category;
    }

    public void setCategory(AttributeCategory category) {
        this.category = category;
    }
}
