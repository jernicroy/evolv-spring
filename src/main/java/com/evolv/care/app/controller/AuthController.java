package com.evolv.care.app.controller;

import com.evolv.care.app.dto.LoginInfo;
import com.evolv.care.app.exception.EVOLV_ERROR;
import com.evolv.care.app.exception.ServerException;
import com.evolv.care.app.security.filter.JwtUtil;
import com.evolv.care.app.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginInfo loginInfo) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginInfo.getUsername(), loginInfo.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String role = userDetails.getAuthorities().stream()
                    .findFirst()
                    .map(GrantedAuthority::getAuthority)
                    .orElse("USER");

            String token = jwtUtil.generateToken(loginInfo.getUsername(), role);
            return ResponseEntity.ok(Map.of("token", token));
        } catch (BadCredentialsException ex){
            throw ServerException.error(EVOLV_ERROR.INVALID_USERNAME_OR_PASS);
        }
    }
}
