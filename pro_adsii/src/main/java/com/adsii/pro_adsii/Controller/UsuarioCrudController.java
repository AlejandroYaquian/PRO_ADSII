package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.Usuario;
import com.adsii.pro_adsii.Service.UsuarioCrudService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/usuario")
@CrossOrigin(origins = "*")

public class UsuarioCrudController {

      private final UsuarioCrudService service;

    public UsuarioCrudController(UsuarioCrudService service) {
        this.service = service;
    }
    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/obtener/{idUsuario}")
    public ResponseEntity<Usuario> obtener(@PathVariable String idUsuario) {
        Usuario u = service.obtener(idUsuario);
        return (u == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(u);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario,
                                     @RequestHeader(value = "X-User", defaultValue = "system") String usuarioAccion) {
        try {
            return ResponseEntity.ok(service.guardar(usuario, usuarioAccion));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{idUsuario}")
    public ResponseEntity<?> eliminar(@PathVariable String idUsuario,
                                  @RequestHeader(value = "X-User", defaultValue = "system") String usuarioAccion) {
         service.eliminar(idUsuario);  
        return ResponseEntity.ok().build();
    }
}
