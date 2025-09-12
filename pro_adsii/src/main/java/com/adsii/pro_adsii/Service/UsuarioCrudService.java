package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.Usuario;
import com.adsii.pro_adsii.Repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class UsuarioCrudService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioCrudService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<Usuario> listar() {
        return usuarioRepository.findAll();
    }

    public Usuario obtener(String idUsuario) {
        return usuarioRepository.findByIdUsuario(idUsuario);
    }

    // Normaliza Strings vacíos a null
    private static String nz(String s) { return (s == null || s.isBlank()) ? null : s.trim(); }

    @Transactional
    public Usuario guardar(Usuario input, String usuarioAccion) {
        final LocalDateTime ahora = LocalDateTime.now();
        final String actor = (usuarioAccion == null || usuarioAccion.isBlank()) ? "system" : usuarioAccion;

        // Normalizaciones básicas
        input.setNombre(nz(input.getNombre()));
        input.setApellido(nz(input.getApellido()));
        input.setCorreoElectronico(nz(input.getCorreoElectronico()));
        input.setPassword(nz(input.getPassword()));
        input.setFotografia(nz(input.getFotografia()));
        input.setTelefonoMovil(nz(input.getTelefonoMovil()));
        input.setPregunta(nz(input.getPregunta()));
        input.setRespuesta(nz(input.getRespuesta()));

        // Defaults para NOT NULL
        if (input.getIntentosDeAcceso() == null) input.setIntentosDeAcceso(0);
        if (input.getRequiereCambiarPassword() == null) input.setRequiereCambiarPassword(false);
        if (input.getSesionActual() == null) input.setSesionActual(false);
        if (input.getIdStatusUsuario() == null) input.setIdStatusUsuario(1); // Activo por defecto (ajústalo si aplica)

        if (input.getIdUsuario() == null || input.getIdUsuario().isBlank()) {
            // ALTA
            input.setIdUsuario(UUID.randomUUID().toString());
            input.setFechaCreacion(ahora);
            input.setUsuarioCreacion(actor);
        } else {
            // EDICIÓN (preserva datos de creación si existe)
            Usuario actual = usuarioRepository.findByIdUsuario(input.getIdUsuario());
            if (actual != null) {
                input.setFechaCreacion(actual.getFechaCreacion());
                input.setUsuarioCreacion(actual.getUsuarioCreacion());
            } else {
                input.setFechaCreacion(ahora);
                input.setUsuarioCreacion(actor);
            }
        }

        input.setFechaModificacion(ahora);
        input.setUsuarioModificacion(actor);

        try {
            return usuarioRepository.save(input);
        } catch (DataIntegrityViolationException dive) {
            // Ayuda a identificar UNIQUE/NOT NULL/length
            throw new IllegalArgumentException("No se pudo guardar el usuario. Revisa campos obligatorios/duplicados. Detalle: " + dive.getMostSpecificCause().getMessage(), dive);
        }
    }

    @Transactional
    public void eliminar(String idUsuario) {
        Usuario u = usuarioRepository.findByIdUsuario(idUsuario);
        if (u == null) throw new IllegalArgumentException("Usuario no encontrado: " + idUsuario);
        usuarioRepository.delete(u);
    }
}
