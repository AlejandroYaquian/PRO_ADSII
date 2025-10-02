package com.adsii.pro_adsii.Service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.Entity.DocumentoPersona;
import com.adsii.pro_adsii.Entity.DocumentoPersonaId;
import com.adsii.pro_adsii.Repository.DocumentoPersonaRepository;

@Service
public class DocumentoPersonaService {

    @Autowired
    private DocumentoPersonaRepository documentoPersonaRepository;

    public List<DocumentoPersona> obtenerTodos() {
        return documentoPersonaRepository.findAll();
    }

    public DocumentoPersona obtenerPorId(int idTipoDocumento, int idPersona) {
        DocumentoPersonaId pk = new DocumentoPersonaId(idTipoDocumento, idPersona);
        return documentoPersonaRepository.findById(pk).orElse(null);
    }

    public DocumentoPersona guardarDocumento(DocumentoPersona documento, String usuario) {
        DocumentoPersonaId pk = documento.getId();
        boolean existe = documentoPersonaRepository.existsById(pk);

        if (!existe) {
            documento.setFechaCreacion(LocalDateTime.now());
            documento.setUsuarioCreacion(usuario);
        } else {
            DocumentoPersona existente = documentoPersonaRepository.findById(pk).orElseThrow();
            documento.setFechaCreacion(existente.getFechaCreacion());
            documento.setUsuarioCreacion(existente.getUsuarioCreacion());
            documento.setFechaModificacion(LocalDateTime.now());
            documento.setUsuarioModificacion(usuario);
        }

        return documentoPersonaRepository.save(documento);
    }

    public void eliminarDocumento(int idTipoDocumento, int idPersona) {
        DocumentoPersonaId pk = new DocumentoPersonaId(idTipoDocumento, idPersona);
        documentoPersonaRepository.deleteById(pk);
    }
}
