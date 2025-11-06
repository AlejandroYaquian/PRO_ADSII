package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.CuentaCorriente;
import com.adsii.pro_adsii.Service.CuentaCorrienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cuentas")
public class CuentaCorrienteController {

    @Autowired
    private CuentaCorrienteService cuentaService;
    
    @GetMapping
    public List<CuentaCorriente> listarCuentas() {
        return cuentaService.listarTodas();
    }

    @GetMapping("/{id}")
    public CuentaCorriente obtenerCuenta(@PathVariable Integer id) {
        return cuentaService.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CuentaCorriente crearCuenta(@RequestBody CuentaCorriente cuenta, @RequestHeader("usuario") String usuario) {
        cuenta.setIdSaldoCuenta(0);
        return cuentaService.guardar(cuenta, usuario);
    }

    @PutMapping("/{id}")
    public CuentaCorriente actualizarCuenta(@PathVariable Integer id, @RequestBody CuentaCorriente cuenta, @RequestHeader("usuario") String usuario) {
        cuenta.setIdSaldoCuenta(id);
        return cuentaService.guardar(cuenta, usuario);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminarCuenta(@PathVariable Integer id) {
        cuentaService.eliminar(id);
    }
    
    @GetMapping("/por-persona/{idPersona}")
    public List<CuentaCorriente> obtenerCuentasPorPersona(@PathVariable int idPersona) {
        return cuentaService.buscarPorIdPersona(idPersona);
    }
}