package com.adsii.pro_adsii.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adsii.pro_adsii.Entity.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, String> {
      Usuario findByIdUsuario(String idUsuario);
    Usuario findByCorreoElectronico(String correoElectronico);

    @Modifying
    @Query("update Usuario u set u.intentosDeAcceso = u.intentosDeAcceso + 1 where u.idUsuario = :id")
    int incrementarIntento(String id);

    @Modifying
    @Query("update Usuario u set u.intentosDeAcceso = 0, u.ultimaFechaIngreso = CURRENT_TIMESTAMP, u.sesionActual = true where u.idUsuario = :id")
    int limpiarIntentosYMarcarSesion(String id);

    @Modifying
    @Query("update Usuario u set u.idStatusUsuario = :idStatus where u.idUsuario = :id")
    int cambiarEstatus(String id, Integer idStatus);
}