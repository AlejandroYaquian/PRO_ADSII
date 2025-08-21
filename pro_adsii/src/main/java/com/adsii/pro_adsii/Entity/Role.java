package com.adsii.pro_adsii.Entity;

import java.util.Date;
import java.util.List;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="ROLE")
public class Role {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional=false)
	@Column(name="IdRole")
	private Integer idRole;

	@Column(name="Nombre")
	private String nombre;

	@Column(name="FechaCreacion")
	private Date fechaCreacion;

	@Column(name="UsuarioCreacion")
	private String usuarioCreacion;

	@Column(name="FechaModificacion")
	private Date fechaModificacion;

	@Column(name="UsuarioModificacion")
	private Date usuarioModificacion;

	@OneToMany(mappedBy = "IdRole", cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	private List<RoleOpcion> roleOpciones;

	public Integer getIdRole() {
		return idRole;
	}

	public void setIdRole(Integer idRole) {
		this.idRole = idRole;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getUsuarioCreacion() {
		return usuarioCreacion;
	}

	public void setUsuarioCreacion(String usuarioCreacion) {
		this.usuarioCreacion = usuarioCreacion;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public Date getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(Date usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

}
