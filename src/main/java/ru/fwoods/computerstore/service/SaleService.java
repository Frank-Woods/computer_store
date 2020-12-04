package ru.fwoods.computerstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.fwoods.computerstore.domain.Sale;
import ru.fwoods.computerstore.repository.SaleRepository;

@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public Page<Sale> getPageSales(Pageable pageable) {
        return saleRepository.findAll(pageable);
    }

    public Sale getSaleById(Long id) {
        return saleRepository.getOne(id);
    }

    public Page<Sale> getSalesPageByUser(Long id, Pageable pageable) {
        return saleRepository.getAllByUserId(id, pageable);
    }
}
