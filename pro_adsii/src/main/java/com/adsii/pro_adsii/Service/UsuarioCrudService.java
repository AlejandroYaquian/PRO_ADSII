package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.Usuario;
import com.adsii.pro_adsii.Repository.UsuarioRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

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

    // ======= Helpers =======
    private static String nz(String s) { return (s == null || s.isBlank()) ? null : s.trim(); }

    /** Hash SHA-256 en hex (minúsculas), sin dependencias externas */
    private static String sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] dig = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(dig.length * 2);
            for (byte b : dig) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 no disponible en la JVM", e);
        }
    }

    @Transactional
    public Usuario guardar(Usuario input, String usuarioAccion) {
        final LocalDateTime ahora = LocalDateTime.now();
        final String actor = (usuarioAccion == null || usuarioAccion.isBlank()) ? "system" : usuarioAccion;

        // Normalizaciones básicas (strings vacíos -> null)
        input.setNombre(nz(input.getNombre()));
        input.setApellido(nz(input.getApellido()));
        input.setCorreoElectronico(nz(input.getCorreoElectronico()));
        input.setPassword(nz(input.getPassword()));          // sólo texto plano cuando venga
        input.setFotografia(nz(input.getFotografia()));      // URL/base64
        input.setTelefonoMovil(nz(input.getTelefonoMovil()));
        input.setPregunta(nz(input.getPregunta()));
        input.setRespuesta(nz(input.getRespuesta()));

        // Defaults para NOT NULL
        if (input.getIntentosDeAcceso() == null) input.setIntentosDeAcceso(0);
        if (input.getRequiereCambiarPassword() == null) input.setRequiereCambiarPassword(false);
        if (input.getSesionActual() == null) input.setSesionActual(false);
        if (input.getIdStatusUsuario() == null) input.setIdStatusUsuario(1); // Activo por defecto (ajústalo si aplica)

        final boolean esAlta = (input.getIdUsuario() == null || input.getIdUsuario().isBlank());

        if (esAlta) {
            // 🔒 Alta: idUsuario debe venir definido por el formulario (NO generar UUID)
            throwIfBlank(input.getIdUsuario(), "idUsuario es requerido");
            // 🔒 Password obligatoria y en hash
            throwIfBlank(input.getPassword(), "Password requerida");
            input.setPassword(sha256(input.getPassword()));

            input.setFechaCreacion(ahora);
            input.setUsuarioCreacion(actor);
        } else {
            // ✏️ Edición: cargar actual para preservar campos de creación y decidir qué actualizar
            Usuario actual = usuarioRepository.findByIdUsuario(input.getIdUsuario());

            if (actual != null) {
                // Preserva datos de creación
                input.setFechaCreacion(actual.getFechaCreacion());
                input.setUsuarioCreacion(actual.getUsuarioCreacion());

                // Si NO vino password nueva -> conservar la actual
                if (input.getPassword() == null) {
                    input.setPassword(actual.getPassword());
                } else {
                    // Vino una nueva en texto plano -> hashear
                    input.setPassword(sha256(input.getPassword()));
                    input.setUltimaFechaCambioPassword(ahora);
                    input.setRequiereCambiarPassword(false);
                }
            } else {
                // Si no existía, se trata como "alta" de facto con el ID provisto
                throwIfBlank(input.getPassword(), "Password requerida");
                input.setPassword(sha256(input.getPassword()));
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
            throw new IllegalArgumentException(
                "No se pudo guardar el usuario. Revisa campos obligatorios/duplicados. Detalle: "
                + dive.getMostSpecificCause().getMessage(), dive);
        }
    }

    @Transactional
    public void eliminar(String idUsuario) {
        Usuario u = usuarioRepository.findByIdUsuario(idUsuario);
        if (u == null) throw new IllegalArgumentException("Usuario no encontrado: " + idUsuario);
        usuarioRepository.delete(u);
    }

    // ===== util =====
    private static void throwIfBlank(String val, String msg) {
        if (val == null || val.isBlank()) throw new IllegalArgumentException(msg);
    }
}
