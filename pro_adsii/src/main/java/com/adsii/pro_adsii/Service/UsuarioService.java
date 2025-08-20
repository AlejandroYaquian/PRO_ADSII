package com.adsii.pro_adsii.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.DTO.LoginResponse;
import com.adsii.pro_adsii.Entity.Usuario;
import com.adsii.pro_adsii.Repository.UsuarioRepository;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public LoginResponse login(String user, String password) {
        Usuario usuario = usuarioRepository.findByIdUsuario(user);

        if (usuario == null) {
            return new LoginResponse(false, "Usuario no existe");
        }

        byte[] hashedPassword = sha256(password);

        if (!Arrays.equals(usuario.getPassword(), hashedPassword)) {
            return new LoginResponse(false, "Contraseña incorrecta");
        }

        if (usuario.getIdStatusUsuario() != 1) {
            return new LoginResponse(false, "Usuario no activo");
        }

        return new LoginResponse(true, "Bienvenido " + usuario.getNombre());
    }

    private byte[] sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generando hash", e);
        }
    }
}