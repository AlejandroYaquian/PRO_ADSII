package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.PeriodoCierreMes;
import com.adsii.pro_adsii.Service.PeriodoCierreMesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/periodo_cierre_mes")
public class PeriodoCierreMesController {

    private final PeriodoCierreMesService service;

    public PeriodoCierreMesController(PeriodoCierreMesService service) {
        this.service = service;
    }

    @GetMapping("/abiertos")
    public List<PeriodoCierreMes> abiertos(){ return service.listarAbiertos(); }

    @GetMapping("/listar")
    public List<PeriodoCierreMes> listar(){ return service.listarTodos(); }

    @PostMapping("/cerrar")
    public ResponseEntity<?> cerrar(@RequestBody Map<String,Integer> body,
                                    @RequestHeader(value="X-User", required=false) String xUser,
                                    @RequestHeader(value="usuario", required=false) String usuarioHdr){
        int anio = body.getOrDefault("anio", 0);
        int mes  = body.getOrDefault("mes", 0);
        if (anio<=0 || mes<=0 || mes>12) return ResponseEntity.badRequest().body("anio/mes inválidos");
        String actor = (xUser!=null && !xUser.isBlank()) ? xUser : (usuarioHdr!=null ? usuarioHdr : "system");
        PeriodoCierreMes p = service.cerrar(anio, mes, actor);
        return ResponseEntity.ok(p);
    }
}
