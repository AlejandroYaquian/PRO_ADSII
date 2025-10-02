package com.adsii.pro_adsii.Entity;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Data
@Embeddable
public class DocumentoPersonaId implements Serializable {
    private Integer idTipoDocumento;
    private Integer idPersona;

    public DocumentoPersonaId() {}

    public DocumentoPersonaId(Integer idTipoDocumento, Integer idPersona) {
        this.idTipoDocumento = idTipoDocumento;
        this.idPersona = idPersona;
    }
}
