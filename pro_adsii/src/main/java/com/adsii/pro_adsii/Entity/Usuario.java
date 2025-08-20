package com.adsii.pro_adsii.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "USUARIO")
@Data
public class Usuario {
   @Id
    @Column(name = "IdUsuario")
    private String idUsuario;

    @Column(name = "Nombre")
    private String nombre;

    @Column(name = "Apellido")
    private String apellido;

    @Column(name = "FechaNacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "IdStatusUsuario")
    private Integer idStatusUsuario;

    @Column(name = "Password")
    private byte[] password;

    @Column(name = "IdGenero")
    private Integer idGenero;

    @Column(name = "UltimaFechaIngreso")
    private LocalDateTime ultimaFechaIngreso;

    @Column(name = "IntentosDeAcceso")
    private Integer intentosDeAcceso;

    @Column(name = "SesionActual")
    private Boolean sesionActual;

    @Column(name = "UltimaFechaCambioPassword")
    private LocalDateTime ultimaFechaCambioPassword;

    @Column(name = "CorreoElectronico")
    private String correoElectronico;

    @Column(name = "RequiereCambiarPassword")
    private Boolean requiereCambiarPassword;

    @Column(name = "Fotografia")
    private String fotografia;

    @Column(name = "TelefonoMovil")
    private String telefonoMovil;

    @Column(name = "IdSucursal")
    private Integer idSucursal;

    @Column(name = "Pregunta")
    private String pregunta;

    @Column(name = "Respuesta")
    private String respuesta;

    @Column(name = "IdRole")
    private Integer idRole;

    @Column(name = "FechaCreacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion")
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion")
    private String usuarioModificacion;
}