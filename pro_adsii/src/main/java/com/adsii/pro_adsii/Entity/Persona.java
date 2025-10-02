package com.adsii.pro_adsii.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "Persona")
public class Persona {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idPersona;

    private String nombre;
    private String apellido;

    private LocalDate fechaNacimiento;

    private int idGenero;
    private String direccion;
    private String telefono;
    private String correoElectronico;

    private int idEstadoCivil;

    private LocalDateTime fechaCreacion;
    private String usuarioCreacion;

    private LocalDateTime fechaModificacion;
    private String usuarioModificacion;
    
}