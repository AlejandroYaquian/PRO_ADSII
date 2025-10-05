
package com.adsii.pro_adsii.Entity;

import java.util.Date;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="MOVIMIENTO_CUENTA")
public class MovimientoCuenta {
    
    @Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional=false)
	@Column(name="IdMovimientoCuenta")
	private Integer idMovimientoCuenta;

   /*  @ManyToOne
    @JoinColumn(name = "IdSaldoCuenta", referencedColumnName = "IdSaldoCuenta")
	private SaldoCuenta saldoCuenta;*/

    @ManyToOne
    @JoinColumn(name = "IdTipoMovimientoCXC", referencedColumnName = "IdTipoMovimientoCXC")
	private TipoMovimientoCXC tipoMovimientoCXC;

    @Column(name="FechaMovimiento")
	private Date fechaMovimiento;

    @Column(name="ValorMovimiento")
	private Double valorMovimiento;

    @Column(name="ValorMovimientoPagado")
	private Double valorMovimientoPagado;

    @Column(name="GeneradoAutomaticamente")
	private Boolean generadoAutomaticamente;

    @Column(name="Descripcion")
	private String descripcion;

    @Column(name="FechaCreacion")
	private Date fechaCreacion;

    @Column(name="UsuarioCreacion")
	private String usuarioCreacion;

	@Column(name="FechaModificacion")
	private Date fechaModificacion;

	@Column(name="UsuarioModificacion")
	private String usuarioModificacion;

    public Integer getIdMovimientoCuenta() {
        return idMovimientoCuenta;
    }

    public void setIdMovimientoCuenta(Integer idMovimientoCuenta) {
        this.idMovimientoCuenta = idMovimientoCuenta;
    }

 
    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Double getValorMovimiento() {
        return valorMovimiento;
    }

    public void setValorMovimiento(Double valorMovimiento) {
        this.valorMovimiento = valorMovimiento;
    }

    public Double getValorMovimientoPagado() {
        return valorMovimientoPagado;
    }

    public void setValorMovimientoPagado(Double valorMovimientoPagado) {
        this.valorMovimientoPagado = valorMovimientoPagado;
    }

    public Boolean getGeneradoAutomaticamente() {
        return generadoAutomaticamente;
    }

    public void setGeneradoAutomaticamente(Boolean generadoAutomaticamente) {
        this.generadoAutomaticamente = generadoAutomaticamente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
