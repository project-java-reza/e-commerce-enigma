package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.Product;
import com.enigma.tokonyareza.entity.ProductPrice;
import com.enigma.tokonyareza.entity.Store;
import com.enigma.tokonyareza.model.request.ProductPriceRequest;
import com.enigma.tokonyareza.repository.ProductPriceRepository;
import com.enigma.tokonyareza.service.ProductPriceService;
import com.enigma.tokonyareza.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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
