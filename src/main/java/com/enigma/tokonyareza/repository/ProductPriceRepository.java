package com.enigma.tokonyareza.repository;

import com.enigma.tokonyareza.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice, String> {

    // ini kondisi dimana kita harus pilih dulu price yang aktif atau tidak
    Optional<ProductPrice> findByProduct_IdAndIsActive(String productId, Boolean active);
}
