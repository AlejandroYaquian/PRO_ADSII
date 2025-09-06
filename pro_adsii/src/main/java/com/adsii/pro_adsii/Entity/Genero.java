package com.adsii.pro_adsii.Entity;

<<<<<<< Updated upstream
    import java.time.LocalDateTime;
=======
import java.time.LocalDateTime;
>>>>>>> Stashed changes

import jakarta.persistence.*;
import lombok.Data;

@Entity
<<<<<<< Updated upstream
@Table(name = "Genero")
@Data
=======
@Data
@Table(name = "Genero")
>>>>>>> Stashed changes
public class Genero {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< Updated upstream
    private Long idGenero;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false)
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 50)
    private String usuarioCreacion;

    private LocalDateTime fechaModificacion;

    @Column(length = 50)
    private String usuarioModificacion;
=======
    @Column(name = "IdGenero")
    private Long IdGenero;

    @Column(name = "Nombre", nullable = false, length = 100)
    private String Nombre;

    @Column(name = "FechaCreacion", nullable = false)
    private LocalDateTime FechaCreacion;

    @Column(name = "UsuarioCreacion", nullable = false, length = 100)
    private String UsuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime FechaModificacion;

    @Column(name = "UsuarioModificacion", length = 100)
    private String UsuarioModificacion;
>>>>>>> Stashed changes
}