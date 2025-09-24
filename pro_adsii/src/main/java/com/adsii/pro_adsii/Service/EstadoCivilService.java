package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.EstadoCivil;
import com.adsii.pro_adsii.Repository.EstadoCivilRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstadoCivilService {

    private final EstadoCivilRepository repo;

    public EstadoCivilService(EstadoCivilRepository repo) {
        this.repo = repo;
    }

    // =======================
    // LISTAR
    // =======================
    public List<EstadoCivil> listar() {
        return repo.findAll();
    }

    // =======================
    // OBTENER
    // =======================
    public EstadoCivil obtener(Integer id) {
        return repo.findById(id).orElse(null);
    }

    // =======================
    // GUARDAR (CREAR/EDITAR)
    // =======================
    public EstadoCivil guardar(EstadoCivil input, String usuarioAccion) {
        final LocalDateTime ahora = LocalDateTime.now();
        final String actor = (usuarioAccion == null || usuarioAccion.trim().isEmpty())
                ? "system" : usuarioAccion;

        // Normalizar nombre
        if (input.getNombre() != null) {
            input.setNombre(input.getNombre().trim());
        }

        if (input.getIdEstadoCivil() == null) {
            // -------- CREAR --------
            validarNombreObligatorio(input.getNombre());
            if (repo.existsByNombreIgnoreCase(input.getNombre())) {
                throw new IllegalArgumentException("Ya existe un Estado Civil con ese nombre");
            }
            input.setFechaCreacion(ahora);
            input.setUsuarioCreacion(actor);
        } else {
            // -------- EDITAR --------
            EstadoCivil actual = repo.findById(input.getIdEstadoCivil())
                    .orElseThrow(() -> new IllegalArgumentException("No existe el Estado Civil"));

            validarNombreObligatorio(input.getNombre());

            // Si cambió el nombre, validar duplicado
            if (!input.getNombre().equalsIgnoreCase(actual.getNombre())
                    && repo.existsByNombreIgnoreCase(input.getNombre())) {
                throw new IllegalArgumentException("Ya existe un Estado Civil con ese nombre");
            }

            // Mantener auditoría de creación original
            input.setFechaCreacion(actual.getFechaCreacion());
            input.setUsuarioCreacion(actual.getUsuarioCreacion());
        }

        // Auditoría de modificación
        input.setFechaModificacion(ahora);
        input.setUsuarioModificacion(actor);

        return repo.save(input);
    }

    // =======================
    // ELIMINAR
    // =======================
    public void eliminar(Integer id) {
        repo.deleteById(id);
    }

    // =======================
    // Helpers
    // =======================
    private void validarNombreObligatorio(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
    }
}
