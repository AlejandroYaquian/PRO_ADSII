package com.adsii.pro_adsii.Repository;

import com.adsii.pro_adsii.Entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, String> {
    Usuario findByIdUsuario(String idUsuario);

}
