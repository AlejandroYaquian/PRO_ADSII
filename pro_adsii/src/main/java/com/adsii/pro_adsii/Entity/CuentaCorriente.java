package com.adsii.pro_adsii.Entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Saldo_Cuenta")
public class CuentaCorriente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSaldoCuenta")
    private Integer idSaldoCuenta;

    // Relación Muchos a Uno con Persona (Cliente)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdPersona", nullable = false) 
    private Persona persona;

    // Relación Muchos a Uno con TipoSaldoCuenta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdTipoSaldoCuenta", nullable = false)
    private TipoSaldoCuenta tipoSaldoCuenta;

    // Relación Muchos a Uno con StatusCuenta
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "IdStatusCuenta", nullable = false)
    private StatusCuenta statusCuenta;

    @Column(name = "NumeroCuenta", nullable = false, length = 20, unique = true)
    private String numeroCuenta;
    
    @Column(name = "FechaApertura", nullable = false)
    private LocalDateTime fechaApertura; 

    // Campos de auditoría
    @Column(name = "FechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, length = 100)
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion", length = 100)
    private String usuarioModificacion;
}