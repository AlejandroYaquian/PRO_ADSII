package com.adsii.pro_adsii.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Entity.Opcion;
import com.adsii.pro_adsii.Service.OpcionService;

@RestController
@RequestMapping("/opcion")

public class OpcionController {

    @Autowired
    private OpcionService opcionService;

    @GetMapping("/listar")
    public List<Opcion> listar() {
        return opcionService.listarTodos();
    }

    @GetMapping("/{id:\\d+}") // Solo acepta números
    public Opcion obtener(@PathVariable Integer id) {
        return opcionService.obtenerPorId(id).orElse(null);
    }

    @PostMapping("/guardar")
    public Opcion guardar(@RequestBody Opcion opcion) {
        return opcionService.guardar(opcion);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        opcionService.eliminar(id);
    }

    @GetMapping("/listarPorMenu/{idMenu}")
    public List<Opcion> listarPorMenu(@PathVariable Integer idMenu) {
        return opcionService.listarPorMenu(idMenu);
    }

}
