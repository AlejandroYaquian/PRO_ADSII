package com.adsii.pro_adsii.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.adsii.pro_adsii.Entity.RoleOpcion;
import com.adsii.pro_adsii.Entity.RoleOpcionId;

public interface RoleOpcionRepository extends JpaRepository<RoleOpcion, RoleOpcionId> {
    
    List<RoleOpcion> findById_IdRole(Integer idRole); 

    RoleOpcion findByIdIdRoleAndIdIdOpcion(Integer idRole, Integer idOpcion);
}
