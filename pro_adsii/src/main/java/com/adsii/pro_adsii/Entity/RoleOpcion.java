package com.adsii.pro_adsii.Entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "ROLE_OPCION")
public class RoleOpcion {

    @EmbeddedId
    private RoleOpcionId id;

    @Column(name="Alta")
    private Boolean alta;

    @Column(name="Baja")
    private Boolean baja;
    
    @Column(name="Cambio")
    private Boolean cambio;

    @Column(name="Imprimir")
    private Boolean imprimir;

    @Column(name="Exportar")
    private Boolean exportar;

    @Column(name="FechaCreacion")
    private Date fechaCreacion;

    @Column(name="UsuarioCreacion")
    private String usuarioCreacion;
    
    @Column(name="FechaModificacion")
    private Date fechaModificacion;
    
    @Column(name="UsuarioModificacion")
    private String usuarioModificacion;

    public RoleOpcionId getId() {
        return id;
    }

    public void setId(RoleOpcionId id) {
        this.id = id;
    }

    public Boolean getAlta() {
        return alta;
    }

    public void setAlta(Boolean alta) {
        this.alta = alta;
    }

    public Boolean getBaja() {
        return baja;
    }

    public void setBaja(Boolean baja) {
        this.baja = baja;
    }

    public Boolean getCambio() {
        return cambio;
    }

    public void setCambio(Boolean cambio) {
        this.cambio = cambio;
    }

    public Boolean getImprimir() {
        return imprimir;
    }

    public void setImprimir(Boolean imprimir) {
        this.imprimir = imprimir;
    }

    public Boolean getExportar() {
        return exportar;
    }

    public void setExportar(Boolean exportar) {
        this.exportar = exportar;
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
