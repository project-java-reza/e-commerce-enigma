package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.Product;
import com.enigma.tokonyareza.repository.ProductRepository;
import com.enigma.tokonyareza.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getById(String id) {
        return productRepository.findById(id).get();
    }

    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product update(Product product) {
        Product currentProduct = getById(product.getId());
                if(currentProduct != null) {
                    return productRepository.save(product);
                }
                return null;
    }

    @Override
    public void delete(String id) {
        productRepository.deleteById(id);
    }
}
