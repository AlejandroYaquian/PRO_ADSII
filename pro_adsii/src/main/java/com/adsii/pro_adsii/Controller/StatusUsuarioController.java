package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.StatusUsuario;
import com.adsii.pro_adsii.Service.StatusUsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/status_usuario")
@CrossOrigin(origins = "*")
public class StatusUsuarioController {
 private final StatusUsuarioService service;

    public StatusUsuarioController(StatusUsuarioService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<StatusUsuario>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<StatusUsuario> obtener(@PathVariable Integer id) {
        StatusUsuario s = service.obtener(id);
        return (s == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(s);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody StatusUsuario status,
                                     @RequestHeader(value = "X-User", defaultValue = "system") String usuarioAccion) {
        return ResponseEntity.ok(service.guardar(status, usuarioAccion));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        service.eliminar(id);
        return ResponseEntity.ok().build();
    }
}
