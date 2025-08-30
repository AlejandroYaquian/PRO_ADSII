package com.adsii.pro_adsii.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "BITACORA_ACCESO")
@Data
public class BitacoraAcceso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdBitacoraAcceso")
    private Long idBitacoraAcceso;

    @Column(name = "IdUsuario")
    private String idUsuario;

    @Column(name = "IdTipoAcceso")
    private Integer idTipoAcceso;

    @Column(name = "FechaAcceso")
    private LocalDateTime fechaAcceso;

    @Column(name = "HttpUserAgent")
    private String httpUserAgent;

    @Column(name = "DireccionIp")
    private String direccionIp;

    @Column(name = "Accion")
    private String accion;

    @Column(name = "SistemaOperativo")
    private String sistemaOperativo;

    @Column(name = "Dispositivo")
    private String dispositivo;

    @Column(name = "Browser")
    private String browser;

    @Column(name = "Sesion")
    private String sesion;
}