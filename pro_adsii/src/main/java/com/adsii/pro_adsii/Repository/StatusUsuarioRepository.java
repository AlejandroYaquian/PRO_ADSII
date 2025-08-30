package com.adsii.pro_adsii.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adsii.pro_adsii.Entity.StatusUsuario;

public interface StatusUsuarioRepository extends JpaRepository<StatusUsuario, Integer>{
    StatusUsuario findByNombre(String nombre);
}
