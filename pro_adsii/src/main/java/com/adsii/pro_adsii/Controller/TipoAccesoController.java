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

import com.adsii.pro_adsii.Entity.TipoAcceso;
import com.adsii.pro_adsii.Service.TipoAccesoService;

@RestController
@RequestMapping("/tipoacceso")
public class TipoAccesoController {

    @Autowired
    private TipoAccesoService tipoAccesoService;

    @GetMapping("/listar")
    public List<TipoAcceso> listar() {
        return tipoAccesoService.listarTodos();
    }

    @GetMapping("/{id}")
    public TipoAcceso obtener(@PathVariable Integer id) {
        return tipoAccesoService.obtenerPorId(id).orElse(null);
    }

    @PostMapping("/guardar")
    public TipoAcceso guardar(@RequestBody TipoAcceso tipoAcceso) {
        String usuarioActual = "Admin"; // usuario logueado
        return tipoAccesoService.guardar(tipoAcceso, usuarioActual);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        tipoAccesoService.eliminar(id);
    }
}
