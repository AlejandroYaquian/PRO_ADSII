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
import java.util.regex.Pattern;

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

    // Helpers 
    private static String nz(String s) { return (s == null || s.isBlank()) ? null : s.trim(); }

    /** Patrón para detectar hash MD5*/
    private static final Pattern MD5_HEX = Pattern.compile("^[a-fA-F0-9]{32}$");

    /** Hash MD5 en hex minúsculas (32 chars) */
    private static String md5(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] dig = md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb = new StringBuilder(32);
            for (byte b : dig) sb.append(String.format("%02x", b));
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 no disponible en la JVM", e);
        }
    }

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
        if (input.getIdStatusUsuario() == null) input.setIdStatusUsuario(1); 

        final boolean esAlta = (input.getIdUsuario() == null || input.getIdUsuario().isBlank());

        if (esAlta) {
            // Alta
            throwIfBlank(input.getIdUsuario(), "idUsuario es requerido");
            // Password obligatoria 
            throwIfBlank(input.getPassword(), "Password requerida");

            // Si NO parece hash MD5, convertirla
            if (!MD5_HEX.matcher(input.getPassword()).matches()) {
                input.setPassword(md5(input.getPassword()));
            }

            input.setFechaCreacion(ahora);
            input.setUsuarioCreacion(actor);

        } else {
            // Edición: cargar actual para preservar campos de creación y decidir qué actualizar
            Usuario actual = usuarioRepository.findByIdUsuario(input.getIdUsuario());

            if (actual != null) {
                // Preserva datos de creación
                input.setFechaCreacion(actual.getFechaCreacion());
                input.setUsuarioCreacion(actual.getUsuarioCreacion());

                // Si NO vino password nueva -> conservar la actual
                if (input.getPassword() == null) {
                    input.setPassword(actual.getPassword());
                } else {
                    // Vino una nueva; si NO parece MD5, convertirla
                    if (!MD5_HEX.matcher(input.getPassword()).matches()) {
                        input.setPassword(md5(input.getPassword()));
                    }
                    input.setUltimaFechaCambioPassword(ahora);
                    input.setRequiereCambiarPassword(false);
                }
            } else {
                // Si no existía, se trata como "alta" de facto con el ID provisto
                throwIfBlank(input.getPassword(), "Password requerida");
                if (!MD5_HEX.matcher(input.getPassword()).matches()) {
                    input.setPassword(md5(input.getPassword()));
                }
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

    // util
    private static void throwIfBlank(String val, String msg) {
        if (val == null || val.isBlank()) throw new IllegalArgumentException(msg);
    }

    // Cambia password tras recuperación 
    @Transactional
    public void actualizarPasswordRecuperacion(String idUsuario, String nuevaPasswordPlana, String usuarioAccion) {
        Usuario u = usuarioRepository.findByIdUsuario(idUsuario);
        if (u == null) throw new IllegalArgumentException("Usuario no existe: " + idUsuario);

        if (nuevaPasswordPlana == null || nuevaPasswordPlana.isBlank()) {
            throw new IllegalArgumentException("Nueva contraseña requerida");
        }
        u.setPassword(md5(nuevaPasswordPlana));
        u.setRequiereCambiarPassword(false);

        u.setUltimaFechaCambioPassword(LocalDateTime.now());

        u.setFechaModificacion(LocalDateTime.now());
        u.setUsuarioModificacion((usuarioAccion == null || usuarioAccion.isBlank()) ? "system" : usuarioAccion);

        usuarioRepository.save(u);
    }
}
