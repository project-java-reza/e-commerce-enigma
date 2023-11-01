package com.enigma.tokonyareza.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfiguration { // disini kita buat aksesnya

    private final AuthTokenFilter authTokenFilter;
    private final AuthEntryPoint authEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }


    // di tokopedia kalau mau liat product-productnya kita ngga harus login dulu, ketika kita masuk ke dalam product nya atau mau beli baru harus login kita bisa buat seperti itu menggunakan antMatchers
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.httpBasic().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(authEntryPoint).and() // untuk mengatur ketika kita gagal autentikasi
                .exceptionHandling().accessDeniedHandler(customAccessDeniedHandler).and() // ketika akses ditolak
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and() // STATELESS management session
                .authorizeRequests() // izin akses ke semua aplikasi
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/products").permitAll() // arti permitAll diberikan aksesnya tanpa authentikasi atau login
                .anyRequest().authenticated()
                .and().addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}


// sampai di pertemuan ini kita sudah butuhin semua, kita sudah ada Entry Point , kita udah ada Token Filter nya, kita udah ada untuk Bcrytput si password nya, kita sudah buat file CustomAccessDenied, kita sudah buat file Jwt Utils nya, file SecurityConfiguration sudah kita buat, nah karena kita sudah buat semanya berarti tinggal kita implementasiin di service nya. kita buat ValidationUtil dulu sebelum buat service
