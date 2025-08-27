package com.adsii.pro_adsii.Controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Entity.Role;
import com.adsii.pro_adsii.Repository.RoleRepository;

@RestController
@RequestMapping(path="/role")
public class RoleController {

    @Autowired
    RoleRepository roleRepository;

    @GetMapping(path="/getRoles")
    public List<Role> obtenerRoles(){
        return roleRepository.findAll();
    }

    @PostMapping("/agregar")
    public Role agregarRole(@RequestBody Role roleAdd) {
        // Puedes setear manualmente campos como FechaCreacion si no se envían desde el frontend
        Date fecha = new Date(); // fecha actual
        roleAdd.setFechaCreacion(fecha);
        roleAdd.setUsuarioCreacion("admin01"); // o tomarlo de sesión si tienes auth
        return roleRepository.save(roleAdd);
    }

    

}
