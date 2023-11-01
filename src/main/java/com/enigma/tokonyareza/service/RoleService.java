package com.enigma.tokonyareza.service;

import com.enigma.tokonyareza.entity.Role;
import com.enigma.tokonyareza.entity.constant.ERole;

public interface RoleService {
    // untuk role service cuma ada 1 method aja
    Role getOrSave(ERole role);
}
