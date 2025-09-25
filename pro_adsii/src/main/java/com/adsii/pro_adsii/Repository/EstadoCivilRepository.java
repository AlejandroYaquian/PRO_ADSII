package com.adsii.pro_adsii.Repository;

import com.adsii.pro_adsii.Entity.EstadoCivil;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstadoCivilRepository extends JpaRepository<EstadoCivil, Integer> {

    // Valida duplicados por nombre (case-insensitive)
    boolean existsByNombreIgnoreCase(String nombre);

    // (Opcional) solo si te gusta el patrón como en UsuarioRepository
    // EstadoCivil findByIdEstadoCivil(Integer idEstadoCivil);
}
