package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.*;
import ru.fwoods.computerstore.repository.SaleRepository;
import ru.fwoods.computerstore.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private SaleProductService saleProductService;

    @Autowired
    private BasketService basketService;

    public Page<Sale> getPageSales(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    public Sale getSaleById(Long id) {
        return saleRepository.getOne(id);
    }

    public Page<Sale> getSalesPageByUser(Long id, Pageable pageable) {
        return saleRepository.getAllByUserId(id, pageable);
    }

    public void sale(User authUser, String address) {
        User user = userService.getUserById(authUser.getId());

        Set<Basket> baskets = user.getBaskets();

        Sale sale = new Sale();

        sale.setUser(user);
        sale.setDate(LocalDateTime.now());
        sale.setAddress(address);

        Sale saleSaved = saleRepository.save(sale);

        baskets.forEach(basket -> {
            for (int i = 0; i < basket.getCount(); i++) {
                SaleProduct saleProduct = new SaleProduct();
                saleProduct.setSale(saleSaved);
                saleProduct.setProductData(basket.getProductData());
                saleProduct.setCost(basket.getProductData().getCost());
                saleProduct.setStatusSale(StatusSale.PROCESSING);
                saleProductService.save(saleProduct);
            }

            basketService.deleteById(basket.getId());
        });
    }

    public void saleUpdate(ru.fwoods.computerstore.model.Sale sale) {
        sale.getSaleProducts().forEach(saleProduct -> {
            SaleProduct saleProductDomain = saleProductService.getSaleProductById(saleProduct.getId());
            for (StatusSale statusSale : StatusSale.values()) {
                if (statusSale.ordinal() == saleProduct.getStatus()) {
                    saleProductDomain.setStatusSale(statusSale);
                }
            }
            saleProductService.save(saleProductDomain);
        });
    }
}
