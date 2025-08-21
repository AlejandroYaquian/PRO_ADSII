package com.adsii.pro_adsii.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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

        String hashedPassword = generarMD5(password);

        if (!usuario.getPassword().equalsIgnoreCase(hashedPassword)) {
            return new LoginResponse(false, "Contraseña incorrecta");
        }

        if (usuario.getIdStatusUsuario() != 1) {
            return new LoginResponse(false, "Usuario no activo");
        }

        return new LoginResponse(true, "Bienvenido " + usuario.getNombre());
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
            throw new RuntimeException("Error generando hash MD5", e);
        }
    }
}
