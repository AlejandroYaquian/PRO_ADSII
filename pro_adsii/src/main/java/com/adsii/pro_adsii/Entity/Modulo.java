package com.adsii.pro_adsii.Entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Modulo")
public class Modulo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_modulo")
    private Long idModulo;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "codigo", nullable = false, length = 20, unique = true)
    private String codigo;

    @Column(name = "estado", nullable = false)
    private Boolean estado;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    // Constructor vacío
    public Modulo() {
    }

    // Constructor completo
    public Modulo(Long idModulo, String nombre, String codigo, Boolean estado, String descripcion) {
        this.idModulo = idModulo;
        this.nombre = nombre;
        this.codigo = codigo;
        this.estado = estado;
        this.descripcion = descripcion;
    }

    // Getters y Setters
    public Long getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(Long idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
