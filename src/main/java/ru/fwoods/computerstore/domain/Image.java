package ru.fwoods.computerstore.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "image")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "error.validation.image.filename.empty")
    @Column(
            name = "filename",
            length = 50,
            nullable = false
    )
    private String filename;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "product_data_id")
    private ProductData productData;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "review_id")
    private Review review;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @OneToOne(
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL
    )
    @JoinColumn(name = "manufacturer_id")
    private Manufacturer manufacturer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public ProductData getProductData() {
        return productData;
    }

    public void setProductData(ProductData productData) {
        this.productData = productData;
    }

    public Review getReview() {
        return review;
    }

    public void setReview(Review review) {
        this.review = review;
    }

    public Promotion getPromotion() {
        return promotion;
    }

    public void setPromotion(Promotion promotion) {
        this.promotion = promotion;
    }

    public Manufacturer getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
}
