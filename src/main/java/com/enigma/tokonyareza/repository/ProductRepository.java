package com.enigma.tokonyareza.repository;

import com.enigma.tokonyareza.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
    Product getProductByName(String productName);
}
