package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.Genero;
import com.adsii.pro_adsii.Repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {

    @Autowired
    private final GeneroRepository generoRepository;

    public GeneroService(GeneroRepository generoRepository) {
        this.generoRepository = generoRepository;
    }

    // Listar todos
    public List<Genero> listarTodos() {
        return generoRepository.findAll();
    }

    // Obtener por ID
    public Optional<Genero> obtenerPorId(Long id) {
        return generoRepository.findById(id);
    }

    public Genero guardar(Genero genero, String usuarioActual) {
        if (genero.getIdGenero() == null) {
            genero.setFechaCreacion(LocalDateTime.now());
            genero.setUsuarioCreacion(usuarioActual);
        } else {
            Genero existente = generoRepository.findById(genero.getIdGenero())
                    .orElseThrow(() -> new RuntimeException("Genero no encontrado"));
            genero.setFechaCreacion(existente.getFechaCreacion());
            genero.setUsuarioCreacion(existente.getUsuarioCreacion());
            genero.setFechaModificacion(LocalDateTime.now());
            genero.setUsuarioModificacion(usuarioActual);
        }
        return generoRepository.save(genero);
    }

    // Eliminar
    public void eliminar(Long id) {
        generoRepository.deleteById(id);
    }
}