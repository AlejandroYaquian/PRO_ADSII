package com.adsii.pro_adsii.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Entity.TipoDocumento;
import com.adsii.pro_adsii.Service.TipoDocumentoService;

@RestController
@RequestMapping("/api/tipodocumento")
public class TipoDocumentoController {

    private final TipoDocumentoService service;

    public TipoDocumentoController(TipoDocumentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<TipoDocumento> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TipoDocumento obtenerPorId(@PathVariable Integer id) {
        return service.buscarPorId(id).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    @PostMapping
    public TipoDocumento crear(@RequestBody TipoDocumento tipoDocumento, @RequestParam String usuario) {
        return service.crear(tipoDocumento, usuario);
    }

    @PutMapping("/{id}")
    public TipoDocumento actualizar(@PathVariable Integer id, @RequestBody TipoDocumento tipoDocumento, @RequestParam String usuario) {
        return service.actualizar(id, tipoDocumento, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}