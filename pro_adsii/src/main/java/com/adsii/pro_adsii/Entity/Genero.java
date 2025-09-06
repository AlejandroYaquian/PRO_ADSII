package com.adsii.pro_adsii.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Genero")

public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
     @Column(name = "IdGenero")
    private Long IdGenero;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String Nombre;

    @Column(name = "FechaCreacion", nullable = false)
    private LocalDateTime FechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, length = 100)
    private String UsuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime FechaModificacion;

    @Column(name = "UsuarioModificacion", length = 100)
    private String UsuarioModificacion;

}