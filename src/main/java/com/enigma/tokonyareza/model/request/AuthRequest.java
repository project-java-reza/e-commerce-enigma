package com.enigma.tokonyareza.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class AuthRequest {

    // auth ini bisa untuk customer dan admin
    private String email;
    private String password;
    private String name;
    private String address;
    private String mobilePhone;
}
