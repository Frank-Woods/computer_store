package ru.fwoods.computerstore.domain;

import javax.persistence.*;

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

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "sale_id", nullable = false)
    private Sale sale;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

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

    public Sale getSale() {
        return sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
