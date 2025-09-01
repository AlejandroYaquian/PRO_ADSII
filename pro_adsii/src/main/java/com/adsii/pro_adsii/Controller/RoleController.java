package com.adsii.pro_adsii.Controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Entity.Role;
import com.adsii.pro_adsii.Service.RoleService;

@RestController
@RequestMapping(path="/role")
public class RoleController {


    @Autowired
    RoleService roleService;

    @GetMapping(path="/getRoles")
    public List<Role> obtenerRoles(){
        return roleService.obtenerRoles();
    }

    @PostMapping("/agregar")
    public Role agregarRole(@RequestBody Role roleAdd) {
        return roleService.guardarRole(roleAdd);
    }


	@GetMapping(path="/buscarRole/{idrole}")
	public Role buscarRole(@PathVariable int idrole) {

		return roleService.buscarRole(idrole);

	}

    

}
