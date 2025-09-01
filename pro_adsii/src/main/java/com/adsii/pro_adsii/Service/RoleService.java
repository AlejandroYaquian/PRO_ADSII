package com.adsii.pro_adsii.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.adsii.pro_adsii.Entity.Role;
import com.adsii.pro_adsii.Repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;

    public Role guardarRole(Role roleAdd){
        Date fecha = new Date(); // fecha actual
        roleAdd.setFechaCreacion(fecha);
        roleAdd.setUsuarioCreacion("admin01"); // o tomarlo de sesión si tienes auth
        return roleRepository.save(roleAdd);
    }


    public List<Role> obtenerRoles(){
        return roleRepository.findAll();
    }


    public Role buscarRole(int idrole) {
        Optional<Role> role = roleRepository.findById(idrole);	
		return role.isPresent() ? role.get() : null;
	}

}
