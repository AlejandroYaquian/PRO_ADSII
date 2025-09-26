package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.Empresa;
import com.adsii.pro_adsii.Service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/empresa")
@CrossOrigin(origins = "*")
public class EmpresaController {

    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/listar")
    public List<Empresa> listar() {
        return empresaService.listarTodos();
    }

    @GetMapping("/{id}")
    public Empresa obtener(@PathVariable Long id) {
        return empresaService.obtenerPorId(id);
    }

    @PostMapping("/guardar")
public Empresa guardar(@RequestBody Empresa empresa) {
    String usuarioActual = empresa.getUsuarioCreacion(); // ← solo funciona si viene en el JSON
    return empresaService.guardar(empresa, usuarioActual);
}


    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        empresaService.eliminar(id);
    }
}
