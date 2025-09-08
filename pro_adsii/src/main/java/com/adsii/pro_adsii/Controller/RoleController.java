package com.adsii.pro_adsii.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.DTO.RoleDTO;
import com.adsii.pro_adsii.Entity.Role;
import com.adsii.pro_adsii.Service.RoleOpcionService;
import com.adsii.pro_adsii.Service.RoleService;

@RestController
@RequestMapping(path="/role")
@CrossOrigin(origins = "*")
public class RoleController {


    @Autowired
    RoleService roleService;

    @Autowired
    RoleOpcionService roleOpcionService;

    @GetMapping(path="/getRoles")
    public List<Role> obtenerRoles(){
        return roleService.obtenerRoles();
    }

    @PostMapping("/agregar")
    public Role agregarRole(@RequestBody RoleDTO roleAdd) {
        Role rol = roleService.guardarRole(roleAdd);
        roleOpcionService.agregarRoleOpcion(rol.getIdRole());
        return rol;
    }


	@GetMapping(path="/buscarRole/{idrole}")
	public Role buscarRole(@PathVariable int idrole) {

		return roleService.buscarRole(idrole);

	}

    @PutMapping(path="/editar")
    public RoleDTO editarRole(@RequestBody RoleDTO roleEdit)
    {
        roleService.editarRole(roleEdit);
        return roleEdit;
    }


    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarRol(@PathVariable Integer id) {
        try {
            roleService.eliminarRol(id);
            return ResponseEntity.ok("Rol eliminado correctamente.");
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body( ex.getMessage());
        }
    }

    

}
