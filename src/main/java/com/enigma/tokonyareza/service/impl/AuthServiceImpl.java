package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.Customer;
import com.enigma.tokonyareza.entity.Role;
import com.enigma.tokonyareza.entity.UserCredential;
import com.enigma.tokonyareza.entity.constant.ERole;
import com.enigma.tokonyareza.model.request.AuthRequest;
import com.enigma.tokonyareza.model.request.RegisterSellerRequest;
import com.enigma.tokonyareza.model.response.LoginResponse;
import com.enigma.tokonyareza.model.response.RegisterResponse;
import com.enigma.tokonyareza.repository.UserCredentialRepository;
import com.enigma.tokonyareza.security.BCryptUtils;
import com.enigma.tokonyareza.security.JwtUtils;
import com.enigma.tokonyareza.service.AuthService;
import com.enigma.tokonyareza.service.CustomerService;
import com.enigma.tokonyareza.service.RoleService;
import com.enigma.tokonyareza.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository userCredentialRepository;
    private final AuthenticationManager authenticationManager;
    private final BCryptUtils bCryptUtils;
    private final CustomerService customerService;
    private final JwtUtils jwtUtils;
    private final ValidationUtil validationUtil;

    private final RoleService roleService;


    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest authRequest) {
        try {

            Role role = roleService.getOrSave(ERole.ROLE_CUSTOMER);

            UserCredential credential= UserCredential.builder()
                    .email(authRequest.getEmail())
                    .password(bCryptUtils.hashPassword(authRequest.getPassword()))
                    .roles(List.of(role))
                    .build();
            UserCredentialRepository.saveAndFlush(credential);

            Customer customer = Customer.builder()
                    .name(authRequest.getName())
                    .address(authRequest.getAddress())
                    .mobilePhone(authRequest.getMobilePhone())
                    .email(authRequest.getEmail())
                    .userCredential(credential)
                    .build();
            customerService.create(customer);

            return RegisterResponse.builder()
                    .email(credential.getEmail())
                    .build();
        } catch (DataIntegrityViolationException exception) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "user already exists");

        }
        return null;
    }

    @Override
    public RegisterResponse registerAdmin(AuthRequest authRequest) {
        return null;
    }

    @Override
    public RegisterResponse registerSeller(RegisterSellerRequest request) {
        return null;
    }

    @Override
    public LoginResponse login(AuthRequest request) {
        return null;
    }
}
