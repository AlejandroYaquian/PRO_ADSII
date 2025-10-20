package com.adsii.pro_adsii.Controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Entity.MovimientoCuenta;
import com.adsii.pro_adsii.Service.MovimientoCuentaService;

@RestController
@RequestMapping("/movimientoCuenta")
public class MovimientoCuentaController {

    @Autowired
    MovimientoCuentaService movimientoCuentaService;
    
   @PostMapping("/agregar")
   public ResponseEntity<?> guardarTipoMovimiento(@RequestBody MovimientoCuenta movimientoCuenta) {
    try {
        MovimientoCuenta guardado = movimientoCuentaService.guardarMovimientoCuenta(movimientoCuenta);
        return ResponseEntity.ok(guardado);
    } catch (RuntimeException e) {
        // Devuelve un 400 Bad Request con el mensaje de error
        Map<String, String> error = new HashMap<>();
        error.put("error", e.getMessage());
        return ResponseEntity.badRequest().body(error);
    }
}


}
