package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.Modulo;
import com.adsii.pro_adsii.Service.ModuloService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/modulo")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @GetMapping("/listar")
    public List<Modulo> listar() {
        return moduloService.listarTodos();
    }

    @GetMapping("/{id}")
    public Modulo obtener(@PathVariable Long id) {
        return moduloService.obtenerPorId(id);
    }

    @PostMapping("/guardar")
    public Modulo guardar(@RequestBody Modulo modulo) {
        String usuarioActual = "Admin"; // usuario real del login
        return moduloService.guardar(modulo, usuarioActual);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        moduloService.eliminar(id);
    }
}
