package com.adsii.pro_adsii.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.DTO.RoleDTO;
import com.adsii.pro_adsii.Entity.Role;
import com.adsii.pro_adsii.Repository.RoleRepository;

@Service
public class RoleService {

    @Autowired
    RoleRepository roleRepository;


    public Role guardarRole(RoleDTO rolNuevo){
        
        Role rol = new Role();
        Date fecha = new Date(); // fecha actual
        rol.setNombre(rolNuevo.getNombre());
        rol.setFechaCreacion(fecha);
        rol.setUsuarioCreacion(rolNuevo.getUsuario()); // o tomarlo de sesión si tienes auth
        rol.setUsuarioModificacion(null);
        rol.setFechaModificacion(null);
        return roleRepository.save(rol);
    }


    public List<Role> obtenerRoles(){
        return roleRepository.findAll();
    }


    public Role buscarRole(int idrole) {
        Optional<Role> role = roleRepository.findById(idrole);	
		return role.isPresent() ? role.get() : null;
	}

    public void editarRole(RoleDTO roleDTO){
        Optional<Role> roMod= roleRepository.findById(roleDTO.getIdRole());
        Date fecha = new Date();
       
            Role rol = roMod.get();
            rol.setNombre(roleDTO.getNombre());
            rol.setFechaModificacion(fecha);
            rol.setUsuarioModificacion(roleDTO.getUsuario());
            roleRepository.save(rol);
    
    }

    public void eliminarRol(Integer idRole) {
        Role role = roleRepository.findById(idRole)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + idRole));

        if (role.getUsuarios() != null && !role.getUsuarios().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el rol porque tiene usuarios vinculados.");
        }

        roleRepository.delete(role);
    }

}
