package com.adsii.pro_adsii.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adsii.pro_adsii.DTO.LoginResponse;
import com.adsii.pro_adsii.Entity.Usuario;
import com.adsii.pro_adsii.Repository.StatusUsuarioRepository;
import com.adsii.pro_adsii.Repository.UsuarioRepository;

@Service
public class UsuarioService {
 private final UsuarioRepository usuarioRepo;
    private final StatusUsuarioRepository statusRepo;

    private static final int MAX_INTENTOS = 5;

    public UsuarioService(UsuarioRepository usuarioRepo, StatusUsuarioRepository statusRepo) {
        this.usuarioRepo = usuarioRepo;
        this.statusRepo = statusRepo;
    }

    @Transactional
    public LoginResponse login(String idUsuarioOcorreo, String passwordPlano) {
        
        Usuario usuario = usuarioRepo.findByIdUsuario(idUsuarioOcorreo);
        if (usuario == null) {
            usuario = usuarioRepo.findByCorreoElectronico(idUsuarioOcorreo);
        }
        if (usuario == null) {
            return new LoginResponse(false, "Credenciales inválidas");
        }

        // Verifica estatus
        String nombreEstatus = statusRepo.findById(usuario.getIdStatusUsuario())
                                         .map(s -> s.getNombre())
                                         .orElse("Desconocido");
        if (!"Activo".equalsIgnoreCase(nombreEstatus)) {
            return new LoginResponse(false, "Usuario " + nombreEstatus + ". Acceso denegado.");
        }

        
        byte[] hashEntrada = sha256(passwordPlano);
        if (!Arrays.equals(hashEntrada, usuario.getPassword())) {

            usuarioRepo.incrementarIntento(usuario.getIdUsuario());

            Usuario u = usuarioRepo.findByIdUsuario(usuario.getIdUsuario());
            if (u.getIntentosDeAcceso() + 1 >= MAX_INTENTOS) {
                Integer idBloqueado = statusRepo.findByNombre("Bloqueado").getIdStatusUsuario();
                usuarioRepo.cambiarEstatus(usuario.getIdUsuario(), idBloqueado);
                return new LoginResponse(false, "Cuenta bloqueada por intentos fallidos.");
            }

            return new LoginResponse(false, "Credenciales inválidas");
        }

        
        usuarioRepo.limpiarIntentosYMarcarSesion(usuario.getIdUsuario());
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