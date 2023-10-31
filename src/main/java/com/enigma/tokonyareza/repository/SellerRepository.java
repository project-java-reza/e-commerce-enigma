package com.enigma.tokonyareza.repository;

import com.enigma.tokonyareza.entity.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SellerRepository extends JpaRepository<Seller, String> {

}
