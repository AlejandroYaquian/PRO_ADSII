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

    // Relación Muchos a Uno con Persona
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPersona", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Persona persona;

    // Relación con TipoSaldoCuenta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTipoSaldoCuenta", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TipoSaldoCuenta tipoSaldoCuenta;

    // Relación con StatusCuenta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdStatusCuenta", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private StatusCuenta statusCuenta;

    @Column(name = "numero_cuenta", nullable = false, length = 20, unique = true)
    private String numeroCuenta;

    @Column(name = "fecha_apertura", nullable = false)
    private LocalDateTime fechaApertura;

    @Column(name = "fecha_creacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "usuario_creacion", nullable = false, length = 100)
    private String usuarioCreacion;

    @Column(name = "fecha_modificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "usuario_modificacion", length = 100)
    private String usuarioModificacion;

    @Column(name = "SaldoAnterior")
    private BigDecimal saldoAnterior;

    @Column(name = "Debitos")
    private BigDecimal debitos;

    @Column(name = "Creditos")
    private BigDecimal creditos;
}