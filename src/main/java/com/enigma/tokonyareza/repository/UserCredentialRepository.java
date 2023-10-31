package com.enigma.tokonyareza.repository;

import com.enigma.tokonyareza.entity.UserCredential;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCredentialRepository extends JpaRepository<UserCredential, String> {

}
