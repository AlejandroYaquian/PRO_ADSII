package com.adsii.pro_adsii.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Modulo")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdModulo")
    private Long IdModulo;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "OrdenMenu", nullable = false)
    private Integer ordenMenu;

    @Column(name = "FechaCreacion", nullable = false)
    private LocalDateTime FechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, length = 50)
    private String UsuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion", length = 50)
    private String usuarioModificacion;

     public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
}