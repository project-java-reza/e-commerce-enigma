package com.enigma.tokonyareza.service;


import com.enigma.tokonyareza.model.request.AuthRequest;
import com.enigma.tokonyareza.model.request.RegisterSellerRequest;
import com.enigma.tokonyareza.model.response.LoginResponse;
import com.enigma.tokonyareza.model.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest authRequest);
    RegisterResponse registerAdmin(AuthRequest authRequest);
    RegisterResponse registerSeller(RegisterSellerRequest request);
    LoginResponse login(AuthRequest request);

    

}
