package com.microservices.auth.controller;

import com.microservices.auth.entity.User;
import com.microservices.auth.security.JwtUtil;
import com.microservices.auth.service.AuthService;
import io.jsonwebtoken.Claims;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService service;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService service, JwtUtil jwtUtil) {
        this.service = service;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return service.register(user);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> req) {
        String token = service.login(req.get("email"), req.get("password"));
        return Map.of("token", token);
    }

    @GetMapping("/validate-token")
    public Claims validate(@RequestHeader("Authorization") String header) {
        String token = header.replace("Bearer ", "");
        return jwtUtil.validate(token);
    }
}
