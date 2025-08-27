package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.Genero;
import com.adsii.pro_adsii.Repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroService {
    @Autowired
    private GeneroRepository generoRepository;

    public List<Genero> listarTodos() {
        return generoRepository.findAll();
    }

    public Optional<Genero> obtenerPorId(Long id) {
        return generoRepository.findById(id);
    }

    public Genero guardar(Genero genero) {
        return generoRepository.save(genero);
    }

    public void eliminar(Long id) {
        generoRepository.deleteById(id);
    }
}
