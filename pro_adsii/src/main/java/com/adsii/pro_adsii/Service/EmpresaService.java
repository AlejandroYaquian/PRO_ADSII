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

    public List<Empresa> listarTodos() {
        return empresaRepository.findAll();
    }

    public Empresa obtenerPorId(Long id) {
        Optional<Empresa> empresa = empresaRepository.findById(id);
        return empresa.orElse(null);
    }

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
        return empresaRepository.save(empresa);
    }

    public void eliminar(Long id) {
        empresaRepository.deleteById(id);
    }
}
