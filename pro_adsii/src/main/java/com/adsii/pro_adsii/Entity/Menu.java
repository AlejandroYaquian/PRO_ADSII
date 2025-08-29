package com.adsii.pro_adsii.Entity;

import java.util.Date;
import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Menu")

public class Menu {
	@Id
	@Column(name = "idmenu")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional=false)
	private int idmenu;
	
	@Column(name = "idmodulo")
	private int idmodulo;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "ordenmenu")
	private int ordenmenu;
	
	@Column(name = "fechacreacion")
	private Date fechacreacion;
	
	@Column(name = "usuariocreacion")
	private String usuariocreacion;
	
	@Column(name = "fechamodificacion")
	private Date fechamodificacion;
	
	@Column(name = "usuariomodificacion")
	private String usuariomodificacion;

	public int getIdmenu() {
		return idmenu;
	}

	public void setIdmenu(int idmenu) {
		this.idmenu = idmenu;
	}

	public int getIdmodulo() {
		return idmodulo;
	}

	public void setIdmodulo(int idmodulo) {
		this.idmodulo = idmodulo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getOrdenmenu() {
		return ordenmenu;
	}

	public void setOrdenmenu(int ordenmenu) {
		this.ordenmenu = ordenmenu;
	}

	public Date getFechacreacion() {
		return fechacreacion;
	}

	public void setFechacreacion(Date fechacreacion) {
		this.fechacreacion = fechacreacion;
	}

	public String getUsuariocreacion() {
		return usuariocreacion;
	}

	public void setUsuariocreacion(String usuariocreacion) {
		this.usuariocreacion = usuariocreacion;
	}

	public Date getFechamodificacion() {
		return fechamodificacion;
	}

	public void setFechamodificacion(Date fechamodificacion) {
		this.fechamodificacion = fechamodificacion;
	}

	public String getUsuariomodificacion() {
		return usuariomodificacion;
	}

	public void setUsuariomodificacion(String usuariomodificacion) {
		this.usuariomodificacion = usuariomodificacion;
	}
	
}
