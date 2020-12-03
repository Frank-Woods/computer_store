package ru.fwoods.computerstore.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

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
    @JoinColumn(name = "attribute_category_id")
    private AttributeCategory category;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "attribute_product_category",
            joinColumns = @JoinColumn(name = "attribute_id"),
            inverseJoinColumns = @JoinColumn(name = "product_category_id")
    )
    private Set<ProductCategory> productCategories;

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

    public Set<ProductCategory> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(Set<ProductCategory> productCategories) {
        this.productCategories = productCategories;
    }
}
