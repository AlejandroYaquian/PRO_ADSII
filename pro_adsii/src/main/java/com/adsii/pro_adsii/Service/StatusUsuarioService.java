package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.StatusUsuario;
import com.adsii.pro_adsii.Repository.StatusUsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StatusUsuarioService {

 private final StatusUsuarioRepository repo;

    public StatusUsuarioService(StatusUsuarioRepository repo) {
        this.repo = repo;
    }

    public List<StatusUsuario> listar() {
        return repo.findAll();
    }

    public StatusUsuario obtener(Integer id) {
        return repo.findById(id).orElse(null);
    }

    public StatusUsuario guardar(StatusUsuario input, String usuarioAccion) {
        LocalDateTime ahora = LocalDateTime.now();
        if (input.getIdStatusUsuario() == null) {
            input.setFechaCreacion(ahora);
            input.setUsuarioCreacion(usuarioAccion);
        } else {
            StatusUsuario actual = repo.findById(input.getIdStatusUsuario()).orElse(null);
            if (actual != null) {
                input.setFechaCreacion(actual.getFechaCreacion());
                input.setUsuarioCreacion(actual.getUsuarioCreacion());
            } else {
                input.setFechaCreacion(ahora);
                input.setUsuarioCreacion(usuarioAccion);
            }
        }
        input.setFechaModificacion(ahora);
        input.setUsuarioModificacion(usuarioAccion);
        return repo.save(input);
    }

    public void eliminar(Integer id) {
        repo.deleteById(id);
    }
}