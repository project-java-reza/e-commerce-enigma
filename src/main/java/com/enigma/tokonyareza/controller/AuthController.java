package com.enigma.tokonyareza.controller;

import com.enigma.tokonyareza.model.request.AuthRequest;
import com.enigma.tokonyareza.model.response.CommonResponse;
import com.enigma.tokonyareza.model.response.LoginResponse;
import com.enigma.tokonyareza.model.response.RegisterResponse;
import com.enigma.tokonyareza.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestBody AuthRequest authRequest) {
        RegisterResponse register = authService.register(authRequest);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Successfully registered")
                .data(register)
                .build();

        return ResponseEntity.status(HttpStatus.CREATED).body(commonResponse);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        LoginResponse responseLogin = authService.login(authRequest);
        CommonResponse<Object> commonResponse = CommonResponse.builder()
                .statusCode(HttpStatus.OK.value())
                .message("Successfully Login")
                .data(responseLogin)
                .build();

        return ResponseEntity.status(HttpStatus.OK).body(commonResponse);
    }

}
