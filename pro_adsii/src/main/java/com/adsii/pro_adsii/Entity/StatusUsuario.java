package com.adsii.pro_adsii.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity @Table(name = "STATUS_USUARIO")
@Data
public class StatusUsuario {
    @Id
    @Column(name = "IdStatusUsuario")
    private Integer idStatusUsuario;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

}
