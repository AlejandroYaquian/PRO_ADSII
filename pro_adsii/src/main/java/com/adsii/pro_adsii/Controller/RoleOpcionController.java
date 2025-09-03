package com.adsii.pro_adsii.Controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.DTO.OpcionDTO;
import com.adsii.pro_adsii.Entity.Opcion;
import com.adsii.pro_adsii.Entity.RoleOpcion;
import com.adsii.pro_adsii.Service.RoleOpcionService;

@RestController
@RequestMapping(path="/RoleOpcion")
public class RoleOpcionController {

   
    @Autowired
    RoleOpcionService roleOpcionService;
    
    @GetMapping("/opcionesRol/{idRole}")
    public List<OpcionDTO> obtenerOpcionesPorRol(@PathVariable Integer idRole) {
        return roleOpcionService.obtenerOpcionesPorRol(idRole);
    }

    @PutMapping(path="/editarRoleOpcion")
    public RoleOpcion actualizarIngrediente(@RequestBody OpcionDTO opcionDTOActualizado) {

        return roleOpcionService.actualizarRoleOpcion(opcionDTOActualizado);

    }

    @GetMapping("/opcionesDisponibles/{idRole}")
    public List<Opcion> agregarOpcionesRole(@PathVariable Integer idRole){

        return roleOpcionService.obtenerOpcionesDiponibles(idRole);
    }


    @PostMapping(path="/agregar")
	public RoleOpcion agregarRoleOpc(@RequestBody OpcionDTO opcionDTO) {
		return roleOpcionService.agregarRoleOpcion(opcionDTO);
	}
	


}
