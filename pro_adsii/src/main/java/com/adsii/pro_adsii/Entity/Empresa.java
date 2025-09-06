package com.adsii.pro_adsii.Entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Empresa")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdEmpresa")
    private Long idEmpresa;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "Direccion", nullable = false, length = 200)
    private String direccion;

    @Column(name = "Nit", nullable = false, length = 20)
    private String nit;

    @Column(name = "PasswordCantidadMayusculas", nullable = false)
    private Integer passwordCantidadMayusculas;

    @Column(name = "PasswordCantidadMinusculas", nullable = false)
    private Integer passwordCantidadMinusculas;

    @Column(name = "PasswordCantidadCaracteresEspeciales", nullable = false)
    private Integer passwordCantidadCaracteresEspeciales;

    @Column(name = "PasswordCantidadCaducidadDias", nullable = false)
    private Integer passwordCantidadCaducidadDias;

    @Column(name = "PasswordLargo", nullable = false)
    private Integer passwordLargo;

    @Column(name = "PasswordIntentosAntesDeBloquear", nullable = false)
    private Integer passwordIntentosAntesDeBloquear;

    @Column(name = "PasswordCantidadNumeros", nullable = false)
    private Integer passwordCantidadNumeros;

    @Column(name = "PasswordCantidadPreguntasValidar", nullable = false)
    private Integer passwordCantidadPreguntasValidar;

    @Column(name = "FechaCreacion", nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, length = 100)
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion", length = 100)
    private String usuarioModificacion;
}
