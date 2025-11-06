package com.adsii.pro_adsii.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.Entity.DocumentoPersona;
import com.adsii.pro_adsii.Entity.DocumentoPersonaId;
import com.adsii.pro_adsii.Entity.Persona;
import com.adsii.pro_adsii.Entity.TipoDocumento;
import com.adsii.pro_adsii.Repository.DocumentoPersonaRepository;
import com.adsii.pro_adsii.Repository.PersonaRepository;
import com.adsii.pro_adsii.Repository.TipoDocumentoRepository;

@Service
public class DocumentoPersonaService {

    @Autowired
    private DocumentoPersonaRepository repo;

    @Autowired
    private PersonaRepository personaRepo;

    @Autowired
    private TipoDocumentoRepository tipoRepo;

    public List<DocumentoPersona> listarPorPersona(int idPersona) {
        return repo.findByPersona_IdPersona(idPersona);
    }

    public DocumentoPersona guardarDocumento(int idPersona, int idTipoDocumento, String noDocumento, String usuario) {
        Persona persona = personaRepo.findById(idPersona)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada"));
        TipoDocumento tipo = tipoRepo.findById(idTipoDocumento)
                .orElseThrow(() -> new RuntimeException("Tipo de documento no encontrado"));

        DocumentoPersonaId id = new DocumentoPersonaId();
        id.setIdPersona(idPersona);
        id.setIdTipoDocumento(idTipoDocumento);

        Optional<DocumentoPersona> existente = repo.findById(id);

        DocumentoPersona doc;
        if (existente.isPresent()) {
            doc = existente.get();
            doc.setNoDocumento(noDocumento);
            doc.setFechaModificacion(LocalDateTime.now());
            doc.setUsuarioModificacion(usuario);
        } else {
            boolean yaExisteTipo = repo.findByPersona_IdPersona(idPersona).stream()
                .anyMatch(d -> d.getTipoDocumento().getIdTipoDocumento() == idTipoDocumento);
            if (yaExisteTipo) {
                throw new RuntimeException("Ya existe un documento de este tipo para esta persona.");
            }

            doc = new DocumentoPersona();
            doc.setId(id);
            doc.setPersona(persona);
            doc.setTipoDocumento(tipo);
            doc.setNoDocumento(noDocumento);
            doc.setFechaCreacion(LocalDateTime.now());
            doc.setUsuarioCreacion(usuario);
        }

        return repo.save(doc);
    }

    public void eliminarDocumento(int idPersona, int idTipoDocumento) {
        DocumentoPersonaId id = new DocumentoPersonaId();
        id.setIdPersona(idPersona);
        id.setIdTipoDocumento(idTipoDocumento);
        repo.deleteById(id);
    }

    public DocumentoPersona obtenerDocumento(int idPersona, int idTipoDocumento) {
        DocumentoPersonaId id = new DocumentoPersonaId();
        id.setIdPersona(idPersona);
        id.setIdTipoDocumento(idTipoDocumento);
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Documento no encontrado"));
    }
}
