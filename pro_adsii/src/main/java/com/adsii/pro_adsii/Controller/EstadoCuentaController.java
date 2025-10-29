package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.DTO.EstadoCuentaDTO;
import com.adsii.pro_adsii.Service.EstadoCuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/estado_cuenta")
@CrossOrigin(origins = "*")
public class EstadoCuentaController {

    @Autowired
    private EstadoCuentaService service;

    @GetMapping("/buscar")
    public List<EstadoCuentaDTO> buscar(
        @RequestParam(required = false) Integer idPersona,
        @RequestParam(required = false) Integer idSaldoCuenta,
        @RequestParam(defaultValue = "") String nombre,
        @RequestParam(defaultValue = "") String apellido,
        // Añadimos las fechas para el periodo de búsqueda
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaInicio,
        @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fechaFin
    ) {
        // NOTA: Para buscar solo por fecha: quitar idPersona, idSaldoCuenta, nombre y apellido, 
        // y solo usar las fechas.
        return service.buscar(idPersona, idSaldoCuenta, nombre, apellido, fechaInicio, fechaFin);
    }

    // Endpoint para simular el "Cierre de Mes"
    @PostMapping("/cierre_mes/{idCuenta}")
    public String cerrarMes(@PathVariable Integer idCuenta) {
        // En un caso real, la fecha debería ser el último día del mes.
        service.realizarCierreDeMes(idCuenta, LocalDateTime.now());
        return "Proceso de Cierre de Mes iniciado para la cuenta " + idCuenta;
    }
}