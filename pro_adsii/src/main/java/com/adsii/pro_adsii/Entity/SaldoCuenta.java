package com.adsii.pro_adsii.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Saldo_Cuenta")
public class SaldoCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdSaldoCuenta")
    private Integer idSaldoCuenta;

    @ManyToOne
    @JoinColumn(name = "IdPersona", nullable = false)
    private Persona persona;

    @ManyToOne
    @JoinColumn(name = "IdTipoSaldoCuenta", nullable = false)
    private TipoSaldoCuenta tipoSaldoCuenta;

    @ManyToOne
    @JoinColumn(name = "IdStatusCuenta", nullable = false)
    private StatusCuenta statusCuenta;

    @Column(name = "SaldoAnterior", nullable = false, precision = 10, scale = 2)
    private BigDecimal saldoAnterior;

    @Column(name = "Debitos", nullable = false, precision = 10, scale = 2)
    private BigDecimal debitos;

    @Column(name = "Creditos", nullable = false, precision = 10, scale = 2)
    private BigDecimal creditos;

    @Column(name = "FechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, length = 100)
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion", length = 100)
    private String usuarioModificacion;
}
