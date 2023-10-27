package com.enigma.tokonyareza.service;

import com.enigma.tokonyareza.entity.Product;
import com.enigma.tokonyareza.model.response.ProductResponse;

import java.util.List;

public interface ProductService {
    Product create(Product product);

    Product getById(String id);

    List<Product> getAll();

    Product update(Product product);

    void delete(String id);

    ProductResponse createProduct(ProductRequest request);
}
