package com.enigma.tokonyareza.service;

import com.enigma.tokonyareza.entity.Role;
import com.enigma.tokonyareza.entity.constant.ERole;

public interface RoleService {
    Role getOrSave(ERole role);
}
