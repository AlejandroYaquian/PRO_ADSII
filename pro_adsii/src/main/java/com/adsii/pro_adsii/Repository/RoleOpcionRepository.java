package com.adsii.pro_adsii.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.adsii.pro_adsii.Entity.RoleOpcion;
import com.adsii.pro_adsii.Entity.RoleOpcionId;

public interface RoleOpcionRepository extends JpaRepository<RoleOpcion, RoleOpcionId> {
     /* 
    @Query("SELECT o FROM Opcion o " +
           "JOIN RoleOpcion ro ON o.idOpcion = ro.id.idOpcion " +
           "WHERE ro.id.idRole = :idRole")
    List<Opcion> findOpcionesByIdRole(@Param("idRole") Integer idRole);*/
}
