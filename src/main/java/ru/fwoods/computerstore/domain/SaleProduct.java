package ru.fwoods.computerstore.domain;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "sale_product")
public class SaleProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            name = "cost",
            length = 10,
            nullable = false
    )
    private Double cost;

    @Column(
            name = "status",
            length = 10,
            nullable = false
    )
    @Enumerated(EnumType.ORDINAL)
    private StatusSale statusSale;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "product_data_id", nullable = false)
    private ProductData productData;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public StatusSale getStatusSale() {
        return statusSale;
    }

    public void setStatusSale(StatusSale statusSale) {
        this.statusSale = statusSale;
    }

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public ProductData getProductData() {
        return productData;
    }

    public void setProductData(ProductData productData) {
        this.productData = productData;
    }
}
