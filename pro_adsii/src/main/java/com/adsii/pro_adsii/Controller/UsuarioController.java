package com.adsii.pro_adsii.Controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.adsii.pro_adsii.DTO.LoginRequest;
import com.adsii.pro_adsii.DTO.LoginResponse;
import com.adsii.pro_adsii.Service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class UsuarioController {
    private final UsuarioService authService;

    public UsuarioController(UsuarioService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return authService.login(request.getIdUsuario(), request.getPassword());
    }
}