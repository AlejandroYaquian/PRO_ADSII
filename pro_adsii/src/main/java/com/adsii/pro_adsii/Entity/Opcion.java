package com.adsii.pro_adsii.entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import com.adsii.pro_adsii.entity.RoleOpcion;
import com.adsii.pro_adsii.entity.Menu;

@Entity
@Table(name = "Opcion")

public class Opcion {
	@Id
	@Column(name = "idopcion")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional=false)
	private int idopcion;
	
	@Column(name = "idmenu")
	private int idmenu;
	
	@Column(name = "nombre")
	private String nombre;
	
	@Column(name = "ordenmenu")
	private int ordenmenu;
	
	@Column(name = "pagina")
	private String pagina;
	
	@Column(name = "fechacreacion")
	private Date fechacreacion;
	
	@Column(name = "usuariocreacion")
	private String usuariocreacion;
	
	@Column(name = "fechamodificacion")
	private Date fechamodificacion;
	
	@Column(name = "usuariomodificacion")
	private String usuariomodificacion;
	

    @OneToMany(mappedBy = "opcion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RoleOpcion> roleOpciones;


	public int getIdopcion() {
		return idopcion;
	}


	public void setIdopcion(int idopcion) {
		this.idopcion = idopcion;
	}


	public int getIdmenu() {
		return idmenu;
	}


	public void setIdmenu(int idmenu) {
		this.idmenu = idmenu;
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


	public String getPagina() {
		return pagina;
	}


	public void setPagina(String pagina) {
		this.pagina = pagina;
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
