package com.adsii.pro_adsii.Entity;

import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "OPCION")
@Data
public class Opcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdOpcion")
    private Integer idOpcion;

    @Column(name = "IdMenu", nullable = false)
    private Integer idMenu;

    @Column(name = "Nombre", nullable = false, length = 255)
    private String nombre;

    @Column(name = "OrdenMenu")
    private Integer ordenMenu;

    @Column(name = "Pagina", length = 255)
    private String pagina;

    @Column(name = "FechaCreacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", updatable = false, length = 100)
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion", length = 100)
    private String usuarioModificacion;
}