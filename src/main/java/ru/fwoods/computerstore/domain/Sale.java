package ru.fwoods.computerstore.domain;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "sale")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(
            name = "date",
            length = 10,
            nullable = false,
            columnDefinition = "datetime"
    )
    private LocalDateTime date;

    @Column(
            name = "address",
            nullable = false
    )
    private String address;

    @ManyToOne(
            fetch = FetchType.EAGER
    )
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "sale")
    private List<SaleProduct> saleProductList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<SaleProduct> getSaleProductList() {
        return saleProductList;
    }

    public void setSaleProductList(List<SaleProduct> saleProductList) {
        this.saleProductList = saleProductList;
    }
}
