package com.adsii.pro_adsii.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.Entity.Persona;
import com.adsii.pro_adsii.Repository.PersonaRepository;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    public List<Persona> obtenerTodas() {
        return personaRepository.findAll();
    }

    public Persona obtenerPorId(int id) {
        return personaRepository.findById(id).orElse(null);
    }

    public Persona guardarPersona(Persona persona, String usuario) {
        if (persona.getIdPersona() == 0) {
            persona.setFechaCreacion(LocalDateTime.now());
            persona.setUsuarioCreacion(usuario);
        } else {
            Persona existente = personaRepository.findById(persona.getIdPersona()).orElse(null);
            if (existente != null) {
                persona.setFechaCreacion(existente.getFechaCreacion());
                persona.setUsuarioCreacion(existente.getUsuarioCreacion());
                persona.setFechaModificacion(LocalDateTime.now());
                persona.setUsuarioModificacion(usuario);
            } else {
                persona.setFechaCreacion(LocalDateTime.now());
                persona.setUsuarioCreacion(usuario);
            }
        }
        return personaRepository.save(persona);
    }

    public void eliminarPersona(int id) {
        personaRepository.deleteById(id);
    }
}