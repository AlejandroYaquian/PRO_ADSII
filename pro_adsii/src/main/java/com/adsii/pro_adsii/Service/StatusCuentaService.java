package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.StatusCuenta;
import com.adsii.pro_adsii.Repository.StatusCuentaRepository;
import jakarta.persistence.EntityNotFoundException; // Añadir esta importación
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class StatusCuentaService {

    @Autowired
    private StatusCuentaRepository statusRepo;

    public List<StatusCuenta> listarTodos() {
        return statusRepo.findAll();
    }

    public StatusCuenta obtenerPorId(Integer id) {
        Optional<StatusCuenta> status = statusRepo.findById(id);
        return status.orElse(null);
    }

    public StatusCuenta guardar(StatusCuenta status, String usuarioActual) {
        if (status.getIdStatusCuenta() == 0) {
            // Lógica para crear
            status.setFechaCreacion(LocalDateTime.now());
            status.setUsuarioCreacion(usuarioActual);
            // Asegurarse de que los campos de modificación están a null/vacío
            status.setFechaModificacion(null);
            status.setUsuarioModificacion(null);
        } else {
            // Lógica para actualizar
            StatusCuenta existente = statusRepo.findById(status.getIdStatusCuenta())
                // MEJORA: Usar EntityNotFoundException
                .orElseThrow(() -> new EntityNotFoundException("StatusCuenta con ID " + status.getIdStatusCuenta() + " no encontrado"));
            
            // Preservar la información de creación
            status.setFechaCreacion(existente.getFechaCreacion());
            status.setUsuarioCreacion(existente.getUsuarioCreacion());
            
            // Establecer la información de modificación
            status.setFechaModificacion(LocalDateTime.now());
            status.setUsuarioModificacion(usuarioActual);
        }
        return statusRepo.save(status);
    }

    public void eliminar(Integer id) {
        statusRepo.deleteById(id);
    }
}