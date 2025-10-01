package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.SaldoCuentaHist;
import com.adsii.pro_adsii.Service.SaldoCuentaHistService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/saldo_cuenta_hist")
public class SaldoCuentaHistController {

    private final SaldoCuentaHistService service;

    public SaldoCuentaHistController(SaldoCuentaHistService service) {
        this.service = service;
    }

    // /saldo_cuenta_hist/listar?anio=2025&mes=9&idPersona=123
    @GetMapping("/listar")
    public List<SaldoCuentaHist> listar(@RequestParam(required=false) Integer anio,
                                        @RequestParam(required=false) Integer mes,
                                        @RequestParam(required=false) Integer idPersona){
        return service.buscar(anio, mes, idPersona);
    }
}
