package com.adsii.pro_adsii.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.Entity.Opcion;
import com.adsii.pro_adsii.Repository.OpcionRepository;

@Service

public class OpcionService {

	@Autowired
	private OpcionRepository opcionRepository;

	public List<Opcion> listarTodos() {
		return opcionRepository.findAll();
	}

	public Optional<Opcion> obtenerPorId(Integer id) {
		return opcionRepository.findById(id);
	}

	public Opcion guardar(Opcion opcion, String usuarioActual) {
		if (opcion.getIdOpcion() == null) {
            opcion.setFechaCreacion(LocalDateTime.now());
            opcion.setUsuarioCreacion(usuarioActual);
        } else {
            Opcion existente = opcionRepository.findById(opcion.getIdOpcion())
                    .orElseThrow(() -> new RuntimeException("Opcion no encontrada"));
            opcion.setFechaCreacion(existente.getFechaCreacion());
            opcion.setUsuarioCreacion(existente.getUsuarioCreacion());
            opcion.setFechaModificacion(LocalDateTime.now());
            opcion.setUsuarioModificacion(usuarioActual);
        }
		return opcionRepository.save(opcion);
	}	

	public void eliminar(Integer id) {
        opcionRepository.deleteById(id);
    }


	public Opcion buscarOpcion(int idopcion) {	
		Optional<Opcion> opcion = opcionRepository.findById(idopcion);	
		return opcion.isPresent() ? opcion.get() : null;
	}

}
