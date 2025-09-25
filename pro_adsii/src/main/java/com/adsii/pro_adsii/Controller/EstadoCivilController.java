package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.EstadoCivil;
import com.adsii.pro_adsii.Service.EstadoCivilService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estado_civil")
@CrossOrigin(origins = "*")
public class EstadoCivilController {

    private final EstadoCivilService service;

    public EstadoCivilController(EstadoCivilService service) {
        this.service = service;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<EstadoCivil>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<EstadoCivil> obtener(@PathVariable Integer id) {
        EstadoCivil ec = service.obtener(id);
        return (ec == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(ec);
    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody EstadoCivil input,
                                     @RequestHeader(value = "X-User", required = false) String xUser) {
        try {
            return ResponseEntity.ok(service.guardar(input, xUser));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error inesperado");
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Integer id) {
        try {
            service.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(409).body("No se puede eliminar: el registro está en uso");
        }
    }
}
