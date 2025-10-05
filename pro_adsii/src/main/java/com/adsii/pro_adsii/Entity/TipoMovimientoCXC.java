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
@Table(name="TIPO_MOVIMIENTO_CXC")
public class TipoMovimientoCXC {

    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional=false)
	@Column(name="IdTipoMovimientoCXC")
	private Integer idTipoMovimientoCXC;

	@Column(name="Nombre")
	private String nombre;

	@Column(name="OperacionCuentaCorriente")
	private Integer operacionCuentaCorriente;

	@Column(name="FechaCreacion")
	private Date fechaCreacion;

	@Column(name="UsuarioCreacion")
	private String usuarioCreacion;

	@Column(name="FechaModificacion")
	private Date fechaModificacion;

	@Column(name="UsuarioModificacion")
	private String usuarioModificacion;

	public Integer getIdTipoMovimientoCXC() {
		return idTipoMovimientoCXC;
	}

	public void setIdTipoMovimientoCXC(Integer idTipoMovimientoCXC) {
		this.idTipoMovimientoCXC = idTipoMovimientoCXC;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getOperacionCuentaCorriente() {
		return operacionCuentaCorriente;
	}

	public void setOperacionCuentaCorriente(Integer operacionCuentaCorriente) {
		this.operacionCuentaCorriente = operacionCuentaCorriente;
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

	public String getUsuarioModificacion() {
		return usuarioModificacion;
	}

	public void setUsuarioModificacion(String usuarioModificacion) {
		this.usuarioModificacion = usuarioModificacion;
	}

	

}
