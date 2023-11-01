package com.enigma.tokonyareza.service.impl;

import com.enigma.tokonyareza.entity.Customer;
import com.enigma.tokonyareza.entity.Role;
import com.enigma.tokonyareza.entity.UserCredential;
import com.enigma.tokonyareza.entity.UserDetailImpl;
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
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserCredentialRepository userCredentialRepository;
    private final BCryptUtils bCryptUtils;
    private final CustomerService customerService;
    private final RoleService roleService;

    private final ValidationUtil validationUtil;
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    @Transactional(rollbackOn = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest authRequest) {
        try {
            // untuk Role dan sebelum itu kita butuh buat file RoleService nya untuk pertama kali buat saat registrasi role nya sebagai apa
            Role role = roleService.getOrSave(ERole.ROLE_CUSTOMER); // ini kita buat role nya
            UserCredential credential= UserCredential.builder() // ini untuk UserCredential
                    .email(authRequest.getEmail())
                    .password(bCryptUtils.hashPassword(authRequest.getPassword()))
                    .roles(List.of(role)) // role ini kita dapet dari List of si role nya
                    .build();
            userCredentialRepository.saveAndFlush(credential);

            Customer customer = Customer.builder() // Role untuk customer
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
    }
    // setelah kita buat AuthService untuk Register selanjutnya kita buat Controller nya



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
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailImpl userDetails = (UserDetailImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

        String token = jwtUtils.generateToken(userDetails.getEmail());
        return LoginResponse.builder()
                .email(userDetails.getEmail())
                .roles(roles)
                .token(token)
                .build();
    }
}
