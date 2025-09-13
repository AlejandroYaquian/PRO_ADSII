package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.DTO.LoginRequest;
import com.adsii.pro_adsii.DTO.LoginResponse;
import com.adsii.pro_adsii.Entity.Usuario;
import com.adsii.pro_adsii.Service.UsuarioCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")
public class UsuarioCrudController {

    @Autowired
    private UsuarioCrudService service;  // inyección simple para evitar error de constructor

    // LISTAR
    @GetMapping("/listar")
    public List<Usuario> listar() {
        return service.listar();
    }

    // OBTENER POR ID (String/UUID)
    @GetMapping("/obtener/{id}")
    public Usuario obtener(@PathVariable String id) {
        return service.obtener(id);
    }

    // GUARDAR (crea/edita). X-User puede venir vacío
    @PostMapping("/guardar")
    public ResponseEntity<Usuario> guardar(
            @RequestBody Usuario input,
            @RequestHeader(value = "X-User", required = false) String xUser) {
        Usuario saved = service.guardar(input, xUser);
        return ResponseEntity.ok(saved);
    }

    // ELIMINAR POR ID (String/UUID)
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable String id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
    
}
