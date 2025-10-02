package com.adsii.pro_adsii.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DOCUMENTO_PERSONA")
public class DocumentoPersona {

    @EmbeddedId
    private DocumentoPersonaId id;

    @ManyToOne
    @MapsId("idTipoDocumento")
    @JoinColumn(name = "IdTipoDocumento")
    private TipoDocumento tipoDocumento;

    @ManyToOne
    @MapsId("idPersona")
    @JoinColumn(name = "IdPersona")
    private Persona persona;

    @Column(name = "NoDocumento")
    private String noDocumento;

    @Column(name = "FechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion")
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion")
    private String usuarioModificacion;

}
