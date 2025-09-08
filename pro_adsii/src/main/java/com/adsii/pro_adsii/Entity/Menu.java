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
@Table(name = "Menu")
@Data
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdMenu")
    private Integer idMenu;

    @Column(name = "IdModulo", nullable = false)
    private Integer idModulo;

    @Column(name = "Nombre", length = 255, nullable = false)
    private String nombre;

    @Column(name = "OrdenMenu")
    private Integer ordenMenu;

    @Column(name = "FechaCreacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", length = 100, updatable = false)
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion", length = 100)
    private String usuarioModificacion;
    
    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

    public Integer getIdModulo() {
		return idModulo;
	}

	public void setIdModulo(Integer idModulo) {
		this.idModulo = idModulo;
	}
}