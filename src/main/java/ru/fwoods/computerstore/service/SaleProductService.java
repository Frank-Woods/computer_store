package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.SaleProduct;
import ru.fwoods.computerstore.repository.SaleProductRepository;

import java.util.List;

@Service
public class SaleProductService {

    @Autowired
    private SaleProductRepository saleProductRepository;

    public List<SaleProduct> getSaleProductsBySale(Long id) {
        return saleProductRepository.getAllBySaleId(id);
    }

    public SaleProduct save(SaleProduct saleProduct) {
        return saleProductRepository.save(saleProduct);
    }

    public SaleProduct getSaleProductById(Long id) {
        return saleProductRepository.getOne(id);
    }
}
