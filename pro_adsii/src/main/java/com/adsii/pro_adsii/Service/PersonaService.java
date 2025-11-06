package com.adsii.pro_adsii.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.Entity.DocumentoPersona;
import com.adsii.pro_adsii.Entity.DocumentoPersonaId;
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
    if (persona.getIdPersona() == null) { 
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

    Persona personaGuardada = personaRepository.save(persona);

    if (persona.getDocumentos() != null && !persona.getDocumentos().isEmpty()) {
        for (DocumentoPersona doc : persona.getDocumentos()) {
            if (doc.getId() == null) {
                DocumentoPersonaId idDoc = new DocumentoPersonaId();
                idDoc.setIdPersona(personaGuardada.getIdPersona());
                idDoc.setIdTipoDocumento(
                    doc.getTipoDocumento() != null ? doc.getTipoDocumento().getIdTipoDocumento() : null
                );
                doc.setId(idDoc);
            } else {
                doc.getId().setIdPersona(personaGuardada.getIdPersona());
            }

            doc.setPersona(personaGuardada);

            if (doc.getFechaCreacion() == null) {
                doc.setFechaCreacion(LocalDateTime.now());
                doc.setUsuarioCreacion(usuario);
            } else {
                doc.setFechaModificacion(LocalDateTime.now());
                doc.setUsuarioModificacion(usuario);
            }
        }

        personaGuardada.setDocumentos(persona.getDocumentos());
        personaGuardada = personaRepository.save(personaGuardada);
    }

    return personaGuardada;
}


    public void eliminarPersona(int id) {
        personaRepository.deleteById(id);
    }
}
