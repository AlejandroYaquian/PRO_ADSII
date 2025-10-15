package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.SaldoCuenta;
import com.adsii.pro_adsii.Service.SaldoCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saldo_cuenta")
@CrossOrigin(origins = "*")
public class SaldoCuentaController {

    @Autowired
    private SaldoCuentaService service;

    @GetMapping("/listar")
    public List<SaldoCuenta> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public SaldoCuenta obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PostMapping("/guardar")
    public SaldoCuenta guardar(@RequestBody SaldoCuenta saldoCuenta) {
        String usuarioActual = saldoCuenta.getUsuarioCreacion();
        return service.guardar(saldoCuenta, usuarioActual);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }

    @GetMapping("/buscar/cuenta")
    public List<SaldoCuenta> buscarPorIdCuenta(@RequestParam int idSaldoCuenta) {
        return service.buscarPorIdCuenta(idSaldoCuenta);
    }

    @GetMapping("/buscar/cliente")
    public List<SaldoCuenta> buscarPorIdCliente(@RequestParam int idPersona) {
        return service.buscarPorIdCliente(idPersona);
    }

    @GetMapping("/buscar/nombre")
    public List<SaldoCuenta> buscarPorNombreApellido(@RequestParam String nombre, @RequestParam String apellido) {
        return service.buscarPorNombreApellido(nombre, apellido);
    }
}
