package com.adsii.pro_adsii.Controller;

import org.springframework.beans.factory.annotation.Autowired;
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
    public MovimientoCuenta guardarTipoMovimiento(@RequestBody MovimientoCuenta movimientoCuenta) {
      return movimientoCuentaService.guardarMovimientoCuenta(movimientoCuenta);
    }

}
