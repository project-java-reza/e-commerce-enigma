package com.enigma.tokonyareza.repository;

import com.enigma.tokonyareza.entity.Role;
import com.enigma.tokonyareza.entity.constant.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByRole(ERole role);
}
