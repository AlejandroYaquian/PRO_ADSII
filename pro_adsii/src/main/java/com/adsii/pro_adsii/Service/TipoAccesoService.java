package com.adsii.pro_adsii.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.Entity.TipoAcceso;
import com.adsii.pro_adsii.Repository.TipoAccesoRepository;

@Service
public class TipoAccesoService {

    @Autowired
    private TipoAccesoRepository tipoAccesoRepository;

    public List<TipoAcceso> listarTodos() {
        return tipoAccesoRepository.findAll();
    }

    public Optional<TipoAcceso> obtenerPorId(Integer id) {
        return tipoAccesoRepository.findById(id);
    }

    public TipoAcceso guardar(TipoAcceso tipoAcceso, String usuarioActual) {
        if (tipoAcceso.getIdTipoAcceso() == null) {
            tipoAcceso.setFechaCreacion(LocalDateTime.now());
            tipoAcceso.setUsuarioCreacion(usuarioActual);
        } else {
            TipoAcceso existente = tipoAccesoRepository.findById(tipoAcceso.getIdTipoAcceso())
                    .orElseThrow(() -> new RuntimeException("TipoAcceso no encontrado"));
            tipoAcceso.setFechaCreacion(existente.getFechaCreacion());
            tipoAcceso.setUsuarioCreacion(existente.getUsuarioCreacion());
            tipoAcceso.setFechaModificacion(LocalDateTime.now());
            tipoAcceso.setUsuarioModificacion(usuarioActual);
        }
        return tipoAccesoRepository.save(tipoAcceso);
    }

    public void eliminar(Integer id) {
        tipoAccesoRepository.deleteById(id);
    }
}