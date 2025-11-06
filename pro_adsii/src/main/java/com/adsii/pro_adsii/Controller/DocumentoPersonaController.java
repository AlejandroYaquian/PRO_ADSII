package com.adsii.pro_adsii.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Entity.DocumentoPersona;
import com.adsii.pro_adsii.Service.DocumentoPersonaService;
import lombok.Data;

@RestController
@RequestMapping("/documento-persona")
public class DocumentoPersonaController {

    @Autowired
    private DocumentoPersonaService service;

    @GetMapping("/persona/{idPersona}")
    public List<DocumentoPersona> listar(@PathVariable int idPersona) {
        return service.listarPorPersona(idPersona);
    }

    @GetMapping("/{idTipoDocumento}/{idPersona}")
public DocumentoPersona obtener(@PathVariable int idTipoDocumento, @PathVariable int idPersona) {
    return service.obtenerDocumento(idPersona, idTipoDocumento);
}


    @PostMapping
    public DocumentoPersona guardar(@RequestBody DocumentoRequest req, @RequestHeader("usuario") String usuario) {
        return service.guardarDocumento(req.getIdPersona(), req.getIdTipoDocumento(), req.getNoDocumento(), usuario);
    }
    

@DeleteMapping("/{idTipoDocumento}/{idPersona}")
public void eliminar(@PathVariable int idTipoDocumento, @PathVariable int idPersona) {
    service.eliminarDocumento(idPersona, idTipoDocumento);
}


    @Data
    public static class DocumentoRequest {
        private int idPersona;
        private int idTipoDocumento;
        private String noDocumento;
    }
}