package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.StatusCuenta;
import com.adsii.pro_adsii.Service.StatusCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statuscuenta") 
public class StatusCuentaController {

    @Autowired
    private StatusCuentaService service;

    @GetMapping
    public List<StatusCuenta> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public StatusCuenta obtener(@PathVariable Integer id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    // MEJORA: Devolver código 201 Created al crear un recurso
    @ResponseStatus(HttpStatus.CREATED) 
    public StatusCuenta crear(@RequestBody StatusCuenta status, @RequestHeader("usuario") String usuarioActual) {
        return service.guardar(status, usuarioActual);
    }

    @PutMapping("/{id}")
    public StatusCuenta actualizar(@PathVariable Integer id, @RequestBody StatusCuenta status, @RequestHeader("usuario") String usuarioActual) {
        status.setIdStatusCuenta(id);
        return service.guardar(status, usuarioActual);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT) // MEJORA: Devolver 204 No Content al eliminar
    public void eliminar(@PathVariable Integer id) {
        service.eliminar(id);
    }
}