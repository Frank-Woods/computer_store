package ru.fwoods.computerstore.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Entity
@Table(name = "product_data")
public class ProductData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "error.validation.productData.name.empty")
    @Column(
            name = "name",
            length = 100,
            nullable = false
    )
    private String name;

    @NotEmpty(message = "error.validation.productData.description.empty")
    @Column(
            name = "description",
            length = 300,
            nullable = false
    )
    private String description;

    @NotEmpty(message = "error.validation.productData.cost.empty")
    @Column(
            name = "cost",
            nullable = false
    )
    private Double cost;

    @NotEmpty(message = "error.validation.productData.shelfLife.empty")
    @Column(name = "shelf_life")
    private Integer shelfLife;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory category;

    @ManyToMany(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinTable(
            name = "product_attribute",
            joinColumns = @JoinColumn(name = "product_data_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_value_id")
    )
    private Set<AttributeValue> attributes;

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

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getShelfLife() {
        return shelfLife;
    }

    public void setShelfLife(Integer shelfLife) {
        this.shelfLife = shelfLife;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Set<AttributeValue> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<AttributeValue> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ProductData{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", cost=" + cost +
                ", shelfLife=" + shelfLife +
                ", manufacturer=" + manufacturer +
                ", country=" + country +
                ", category=" + category +
                ", attributes=" + attributes +
                '}';
    }
}
