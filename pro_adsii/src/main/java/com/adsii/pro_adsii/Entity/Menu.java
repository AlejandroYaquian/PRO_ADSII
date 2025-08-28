package com.adsii.pro_adsii.entity;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="MENU")

public class Menu {
	@Id
	@Column(name = "IdMenu")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional=false)
	private int IdMenu;
	
	@Column(name = "IdModulo")
	private int IdModulo;
	
	@Column(name = "Nombre")
	private String Nombre;
	
	@Column(name = "OrdenMenu")
	private int OrdenMenu;
	
	@Column(name = "FechaCreacion")
	private Date FechaCreacion;
	
	@Column(name = "UsuarioCreacion")
	private String UsuarioCreacion;
	
	@Column(name = "FechaModificacion")
	private Date FechaModificacion;
	
	@Column(name = "UsuarioModificacion")
	private String UsuarioModificacion;

	public int getIdMenu() {
		return IdMenu;
	}

	public void setIdMenu(int idMenu) {
		IdMenu = idMenu;
	}

	public int getIdModulo() {
		return IdModulo;
	}

	public void setIdModulo(int idModulo) {
		IdModulo = idModulo;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public int getOrdenMenu() {
		return OrdenMenu;
	}

	public void setOrdenMenu(int ordenMenu) {
		OrdenMenu = ordenMenu;
	}

	public Date getFechaCreacion() {
		return FechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		FechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return UsuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		UsuarioCreacion = usuarioCreacion;
	}

	public Date getFechaModificacion() {
		return FechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		FechaModificacion = fechaModificacion;
	}

	public String getUsuarioModificacion() {
		return UsuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		UsuarioModificacion = usuarioModificacion;
	}
}
