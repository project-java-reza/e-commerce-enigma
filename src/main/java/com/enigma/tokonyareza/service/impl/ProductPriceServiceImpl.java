package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.ProductPrice;
import com.enigma.tokonyareza.repository.ProductPriceRepository;
import com.enigma.tokonyareza.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {

    private final ProductPriceRepository productPriceRepository;

    @Override
    public ProductPrice create(ProductPrice productPrice) {
        return productPriceRepository.save(productPrice);
    }

    @Override
    public ProductPrice getById(String id) {
        return productPriceRepository.findById(id).get();
    }

    @Override
    public ProductPrice findProductPriceActive(String productId, Boolean active) {
        return productPriceRepository.findByProduct_IdAndIsActive(productId,active).orElseThrow();
    }
}
