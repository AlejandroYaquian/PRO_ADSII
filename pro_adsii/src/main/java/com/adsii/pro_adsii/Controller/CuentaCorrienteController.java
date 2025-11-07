package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.CuentaCorriente;
import com.adsii.pro_adsii.Service.CuentaCorrienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaCorrienteController {

    @Autowired
    private CuentaCorrienteService service;

    @GetMapping
    public List<CuentaCorriente> listar() {
        return service.listar();
    }

    @GetMapping("/persona/{idPersona}")
    public List<CuentaCorriente> listarPorPersona(@PathVariable Integer idPersona) {
        return service.listarPorPersona(idPersona);
    }

    @GetMapping("/{id}")
    public CuentaCorriente obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    public CuentaCorriente crear(@RequestBody CuentaCorriente cuenta, @RequestHeader("usuario") String usuario) {
        return service.crear(cuenta, usuario);
    }

    @PutMapping("/{id}")
    public CuentaCorriente actualizar(@PathVariable Integer id, @RequestBody CuentaCorriente cuenta, @RequestHeader("usuario") String usuario) {
        return service.actualizar(id, cuenta, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}