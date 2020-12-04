package ru.fwoods.computerstore.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;
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
    private Integer cost;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "manufacturer_id", nullable = false)
    private Manufacturer manufacturer;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory category;

    @ManyToMany(
            fetch = FetchType.EAGER
    )
    @JoinTable(
            name = "product_attribute",
            joinColumns = @JoinColumn(name = "product_data_id"),
            inverseJoinColumns = @JoinColumn(name = "attribute_value_id")
    )
    private Set<AttributeValue> attributes;

    @OneToMany(mappedBy = "productData")
    private List<PromotionProduct> promotionProducts;

    @OneToMany(mappedBy = "productData")
    private List<Image> images;

    @OneToMany(mappedBy = "productData")
    private List<Review> reviews;

    @OneToMany(mappedBy = "prductData")
    private List<SaleProduct> saleProducts;

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

    public List<PromotionProduct> getPromotionProducts() {
        return promotionProducts;
    }

    public void setPromotionProducts(List<PromotionProduct> promotionProducts) {
        this.promotionProducts = promotionProducts;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public List<SaleProduct> getSaleProducts() {
        return saleProducts;
    }

    public void setSaleProducts(List<SaleProduct> saleProducts) {
        this.saleProducts = saleProducts;
    }
}
