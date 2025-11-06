package com.adsii.pro_adsii.Entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;

@Embeddable
public class DocumentoPersonaId implements Serializable {
    private Integer idPersona;
    private Integer idTipoDocumento;
    
    public Integer getIdPersona() {
        return idPersona;
    }
    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }
    public Integer getIdTipoDocumento() {
        return idTipoDocumento;
    }
    public void setIdTipoDocumento(Integer idTipoDocumento) {
        this.idTipoDocumento = idTipoDocumento;
    }
}
