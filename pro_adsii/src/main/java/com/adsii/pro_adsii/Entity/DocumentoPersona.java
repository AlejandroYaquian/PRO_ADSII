package com.adsii.pro_adsii.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "DOCUMENTO_PERSONA")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class DocumentoPersona {

    @EmbeddedId
    private DocumentoPersonaId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idPersona")
    @JoinColumn(name = "IdPersona", nullable = false)
    @JsonBackReference
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("idTipoDocumento")
    @JoinColumn(name = "IdTipoDocumento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "NoDocumento")
    private String noDocumento;

    @Column(name = "FechaCreacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion")
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion")
    private String usuarioModificacion;
}