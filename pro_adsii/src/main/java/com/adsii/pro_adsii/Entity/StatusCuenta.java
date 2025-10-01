package com.adsii.pro_adsii.Entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Status_Cuenta")
public class StatusCuenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdStatusCuenta")
    private int idStatusCuenta;

    @Column(name = "Nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "FechaCreacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion", length = 100)
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion", length = 100)
    private String usuarioModificacion;

    public StatusCuenta() {}

    // Getters y Setters
    public int getIdStatusCuenta() { return idStatusCuenta; }
    public void setIdStatusCuenta(int idStatusCuenta) { this.idStatusCuenta = idStatusCuenta; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getUsuarioCreacion() { return usuarioCreacion; }
    public void setUsuarioCreacion(String usuarioCreacion) { this.usuarioCreacion = usuarioCreacion; }

    public LocalDateTime getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(LocalDateTime fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public String getUsuarioModificacion() { return usuarioModificacion; }
    public void setUsuarioModificacion(String usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }
}