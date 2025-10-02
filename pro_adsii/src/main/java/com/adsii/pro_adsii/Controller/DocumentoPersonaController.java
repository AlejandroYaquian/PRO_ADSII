package com.adsii.pro_adsii.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Entity.DocumentoPersona;
import com.adsii.pro_adsii.Entity.DocumentoPersonaId;
import com.adsii.pro_adsii.Entity.Persona;
import com.adsii.pro_adsii.Entity.TipoDocumento;
import com.adsii.pro_adsii.Service.DocumentoPersonaService;
import com.adsii.pro_adsii.Service.PersonaService;
import com.adsii.pro_adsii.Service.TipoDocumentoService;

@RestController
@RequestMapping("/documento")
public class DocumentoPersonaController {

    @Autowired
    private DocumentoPersonaService documentoPersonaService;

    @GetMapping
    public List<DocumentoPersona> listarDocumentos() {
        return documentoPersonaService.obtenerTodos();
    }

    @GetMapping("/{idTipoDocumento}/{idPersona}")
    public DocumentoPersona obtenerDocumento(@PathVariable int idTipoDocumento,
            @PathVariable int idPersona) {
        return documentoPersonaService.obtenerPorId(idTipoDocumento, idPersona);
    }

    @PostMapping
    public ResponseEntity<?> crearDocumento(@RequestBody DocumentoPersona documento,
            @RequestHeader("usuario") String usuario) {
        try {
            DocumentoPersonaId id = new DocumentoPersonaId(
                    documento.getId().getIdTipoDocumento(),
                    documento.getId().getIdPersona());
            documento.setId(id);

            TipoDocumento tipo = new TipoDocumento();
            tipo.setIdTipoDocumento(id.getIdTipoDocumento());
            documento.setTipoDocumento(tipo);

            Persona persona = new Persona();
            persona.setIdPersona(id.getIdPersona());
            documento.setPersona(persona);

            DocumentoPersona guardado = documentoPersonaService.guardarDocumento(documento, usuario);
            return ResponseEntity.ok(guardado);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar documento: " + e.getMessage());
        }
    }

    @PutMapping("/{idTipoDocumento}/{idPersona}")
    public DocumentoPersona actualizarDocumento(@PathVariable int idTipoDocumento,
            @PathVariable int idPersona,
            @RequestBody DocumentoPersona documento,
            @RequestHeader("usuario") String usuario) {
        documento.getId().setIdTipoDocumento(idTipoDocumento);
        documento.getId().setIdPersona(idPersona);
        return documentoPersonaService.guardarDocumento(documento, usuario);
    }

    @DeleteMapping("/{idTipoDocumento}/{idPersona}")
    public void eliminarDocumento(@PathVariable int idTipoDocumento,
            @PathVariable int idPersona) {
        documentoPersonaService.eliminarDocumento(idTipoDocumento, idPersona);
    }

    @Autowired
    private PersonaService personaService;

    @GetMapping("/listarPersonas")
    public List<Persona> listarPersonas() {
        return personaService.obtenerTodas();
    }

    @Autowired
    private TipoDocumentoService tipoDocumentoService;

    @GetMapping("/listarTipoDocumento")
    public List<TipoDocumento> listarTipoDocumento() {
        return tipoDocumentoService.listar();
    }
}
