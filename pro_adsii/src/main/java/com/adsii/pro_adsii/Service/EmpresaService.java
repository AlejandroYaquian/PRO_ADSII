package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.Empresa;
import com.adsii.pro_adsii.Repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;

    // Listar todas las empresas
    public List<Empresa> listarTodos() {
        return empresaRepository.findAll();
    }

    // Obtener una empresa por ID
    public Optional<Empresa> obtenerPorId(Long id) {
    return empresaRepository.findById(id);
    }

    // Guardar o actualizar una empresa
    public Empresa guardar(Empresa empresa, String usuarioActual) {
        if (empresa.getIdEmpresa() == null) {
            empresa.setFechaCreacion(LocalDateTime.now());
            empresa.setUsuarioCreacion(usuarioActual);
        } else {
            Empresa existente = empresaRepository.findById(empresa.getIdEmpresa())
                    .orElseThrow(() -> new RuntimeException("Empresa no encontrada"));

            empresa.setFechaCreacion(existente.getFechaCreacion());
            empresa.setUsuarioCreacion(existente.getUsuarioCreacion());
            empresa.setFechaModificacion(LocalDateTime.now());
            empresa.setUsuarioModificacion(usuarioActual);
        }

        // Asegurar que todos los campos estén presentes
        validarCamposObligatorios(empresa);

        return empresaRepository.save(empresa);
    }

    // Eliminar una empresa por ID
    public void eliminar(Long idEmpresa) {
        empresaRepository.deleteById(idEmpresa);
    }

    // Validación básica de campos obligatorios
    private void validarCamposObligatorios(Empresa empresa) {
        if (empresa.getNombre() == null || empresa.getDireccion() == null || empresa.getNit() == null) {
            throw new IllegalArgumentException("Nombre, Dirección y NIT son obligatorios.");
        }

        if (empresa.getPasswordCantidadMayusculas() == null ||
            empresa.getPasswordCantidadMinusculas() == null ||
            empresa.getPasswordCantidadCaracteresEspeciales() == null ||
            empresa.getPasswordCantidadCaducidadDias() == null ||
            empresa.getPasswordLargo() == null ||
            empresa.getPasswordIntentosAntesDeBloquear() == null ||
            empresa.getPasswordCantidadNumeros() == null ||
            empresa.getPasswordCantidadPreguntasValidar() == null) {
            throw new IllegalArgumentException("Todos los campos de política de contraseña son obligatorios.");
        }
    }
}
