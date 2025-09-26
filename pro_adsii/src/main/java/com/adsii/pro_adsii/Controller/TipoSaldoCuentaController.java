package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.TipoSaldoCuenta;
import com.adsii.pro_adsii.Service.TipoSaldoCuentaService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipo-saldo")
public class TipoSaldoCuentaController {

    @Autowired
    private TipoSaldoCuentaService tipoService;

    @GetMapping("/listar")
    public List<TipoSaldoCuenta> listar() {
        return tipoService.listarTodos();
    }

    @GetMapping("/{id}")
    public TipoSaldoCuenta obtener(@PathVariable Long id) {
        return tipoService.obtenerPorId(id);
    }

   @PostMapping("/guardar")
public TipoSaldoCuenta guardar(@RequestBody TipoSaldoCuenta tipo) {
    String usuarioActual = tipo.getUsuarioCreacion(); // ← viene desde el frontend
    return tipoService.guardar(tipo, usuarioActual);
}


    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        tipoService.eliminar(id);
    }
}
