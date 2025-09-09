package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.Usuario;
import com.adsii.pro_adsii.Repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UsuarioCrudService {

 public static final int STATUS_INACTIVO_ID = 2;

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
 @Transactional
    public Usuario guardar(Usuario input, String usuarioAccion) {
        LocalDateTime ahora = LocalDateTime.now();

        if (input.getIdUsuario() == null || input.getIdUsuario().isBlank()) {
            // Alta
            input.setFechaCreacion(ahora);
            input.setUsuarioCreacion(usuarioAccion);
        } else {
            // Edición
            Usuario actual = usuarioRepository.findByIdUsuario(input.getIdUsuario());
            if (actual != null) {
                input.setFechaCreacion(actual.getFechaCreacion());
                input.setUsuarioCreacion(actual.getUsuarioCreacion());
            } else {
                
                input.setFechaCreacion(ahora);
                input.setUsuarioCreacion(usuarioAccion);
            }
        }

        //modificación
        input.setFechaModificacion(ahora);
        input.setUsuarioModificacion(usuarioAccion);

        return usuarioRepository.save(input);
    }
      @Transactional
public void eliminar(String idUsuario) {
    Usuario u = usuarioRepository.findByIdUsuario(idUsuario);
    if (u == null) throw new IllegalArgumentException("Usuario no encontrado");
    usuarioRepository.delete(u);   
}
}


