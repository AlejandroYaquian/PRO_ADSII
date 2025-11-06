package com.adsii.pro_adsii.Entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "TIPO_DOCUMENTO")
@Data
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class TipoDocumento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoDocumento;

    @Column(nullable = false, length = 100)
    private String nombre;

    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;

    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
}