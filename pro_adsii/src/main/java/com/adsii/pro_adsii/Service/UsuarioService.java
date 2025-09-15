package com.adsii.pro_adsii.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.DTO.LoginResponse;
import com.adsii.pro_adsii.Entity.Usuario;
import com.adsii.pro_adsii.Repository.UsuarioRepository;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final BitacoraAccesoService bitacoraService;

    public UsuarioService(UsuarioRepository usuarioRepository, BitacoraAccesoService bitacoraService) {
        this.usuarioRepository = usuarioRepository;
        this.bitacoraService = bitacoraService;
    }

    public LoginResponse login(String user, String password, HttpServletRequest request) {
    Usuario usuario = usuarioRepository.findByIdUsuario(user);

    if (usuario == null) {
        bitacoraService.registrarAcceso(user, 4, request, "Usuario no existe");
        return new LoginResponse(false, "Usuario no existe", null);
    }

    String hashedPassword = generarMD5(password);

    if (!usuario.getPassword().equalsIgnoreCase(hashedPassword)) {
        bitacoraService.registrarAcceso(user, 2, request, "Contraseña incorrecta");
        return new LoginResponse(false, "Contraseña incorrecta", null);
    }

    if (usuario.getIdStatusUsuario() != 1) {
        bitacoraService.registrarAcceso(user, 3, request, "Usuario inactivo");
        return new LoginResponse(false, "Usuario no activo", null);
    }

    bitacoraService.registrarAcceso(user, 1, request, "Login exitoso");
    return new LoginResponse(true, "Bienvenido " + usuario.getNombre(), usuario.getIdUsuario());
}


    private String generarMD5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(input.getBytes(StandardCharsets.UTF_8));

            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generando", e);
        }
    }

    
}

