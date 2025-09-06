package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.Empresa;
import com.adsii.pro_adsii.Service.EmpresaService;
import java.util.Optional;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/listar")
    public List<Empresa> listar() {
        return empresaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Empresa obtener(@PathVariable Long id) {
        return empresaService.obtenerPorId(id).orElse(null);
    }

    @PostMapping("/guardar")
    public Empresa guardar(@RequestBody Empresa empresa) {
        String usuarioActual = "Admin"; // usuario real del login
        return empresaService.guardar(empresa, usuarioActual);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        empresaService.eliminar(id);
    }
}
