package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.SaleProduct;
import ru.fwoods.computerstore.repository.SaleProductRepository;

import java.util.List;

@Service
public class SaleProductService {

    @Autowired
    private SaleProductRepository saleProductRepository;

    public Page<SaleProduct> getSaleProductsBySale(Long id, Pageable pageable) {
        return saleProductRepository.getAllBySaleId(id, pageable);
    }

    public SaleProduct save(SaleProduct saleProduct) {
        return saleProductRepository.save(saleProduct);
    }

    public SaleProduct getSaleProductById(Long id) {
        return saleProductRepository.getOne(id);
    }
}
