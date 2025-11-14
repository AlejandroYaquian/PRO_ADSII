package com.adsii.pro_adsii.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Data
@Table(name = "Saldo_Cuenta")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CuentaCorriente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSaldoCuenta")
    private Integer idSaldoCuenta;
    
    // EL NUMERO DE CUENTA SERÁ EL MISMO ID. En la BD debe estar como NOT NULL.
    @Column(name = "numero_cuenta", length = 20) 
    private String numeroCuenta; 

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPersona", nullable = false) // Un Persona (Cliente) tiene varias Cuentas
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Persona persona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTipoSaldoCuenta", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoSaldoCuenta tipoSaldoCuenta;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdStatusCuenta", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StatusCuenta statusCuenta;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDateTime fechaApertura;

    // --- CAMPOS DE SALDO ---
    @Column(name = "SaldoAnterior", precision = 10, scale = 2)
    private BigDecimal saldoAnterior;

    @Column(name = "Debitos", precision = 10, scale = 2)
    private BigDecimal debitos;

    @Column(name = "Creditos", precision = 10, scale = 2)
    private BigDecimal creditos;
    // ----------------------

    // --- CAMPOS DE AUDITORÍA ---
    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion", nullable = false, length = 50)
    private String usuarioCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_modificacion", length = 50)
    private String usuarioModificacion;
    // --------------------------------------------------------------------
}