package com.adsii.pro_adsii.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.adsii.pro_adsii.Entity.TipoMovimientoCXC;
import com.adsii.pro_adsii.Service.TipoMovimientoCXCService;


@RestController
@RequestMapping("/tipoMovimiento")
public class TipoMovimientoCXCController {

    @Autowired
    TipoMovimientoCXCService tipoMovCXCService;

    @GetMapping(path="/buscarTipoMovimiento/{idTipoMovimiento}")
	public TipoMovimientoCXC buscarTipoMovimiento(@PathVariable int idTipoMovimiento) {
		return tipoMovCXCService.buscarTipoMovimiento(idTipoMovimiento);
	}

    @PostMapping("/agregar")
    public TipoMovimientoCXC guardarTipoMovimiento(@RequestBody TipoMovimientoCXC tipoMovCXC) {
      return tipoMovCXCService.guardarTipoMovimiento(tipoMovCXC);
    }

    @GetMapping(path="/getTipoMovimientos")
    public List<TipoMovimientoCXC> obtenerRoles(){
        return tipoMovCXCService.obtenerTipoMovimientosCXC();
    }


    @PutMapping(path="/editar")
    public TipoMovimientoCXC editarTipoMovimiento(@RequestBody TipoMovimientoCXC tipoMovimientoCXC)
    {
        tipoMovCXCService.editarTipoMovCXC(tipoMovimientoCXC);
        return tipoMovimientoCXC;
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable Integer id) {
        try {
            tipoMovCXCService.eliminarTipoMovCXC(id);
            return ResponseEntity.ok("Tipo Movimiento CXC eliminado correctamente.");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body( ex.getMessage());
        }
    }

}
