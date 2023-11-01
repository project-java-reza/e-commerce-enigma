package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.Role;
import com.enigma.tokonyareza.entity.constant.ERole;
import com.enigma.tokonyareza.repository.RoleRepository;
import com.enigma.tokonyareza.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole role) {
        return roleRepository.findByRole(role).orElseGet(()-> roleRepository.save(Role.builder()
                .role(role)
                .build()));
    }
}
