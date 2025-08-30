package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.Sucursal;
import com.adsii.pro_adsii.Service.SucursalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sucursal")
public class SucursalController {

    @Autowired
    private SucursalService sucursalService;

    @GetMapping
    public List<Sucursal> listarSucursales() {
        return sucursalService.obtenerTodas();
    }

    @PostMapping
    public Sucursal crearSucursal(@RequestBody Sucursal sucursal) {
        return sucursalService.guardarSucursal(sucursal);
    }

    @GetMapping("/{id}")
    public Sucursal obtenerSucursal(@PathVariable int id) {
        return sucursalService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminarSucursal(@PathVariable int id) {
        sucursalService.eliminarSucursal(id);
    }
}
