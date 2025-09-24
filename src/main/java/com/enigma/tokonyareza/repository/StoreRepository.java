package com.enigma.tokonyareza.repository;

import com.enigma.tokonyareza.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, String> {

}
