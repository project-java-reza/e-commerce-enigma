package com.enigma.tokonyareza.model.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class RegisterSellerRequest {
    private String email;
    private String password;
    private String username;
    private String storeName;
    private String mobilePhone;
}
