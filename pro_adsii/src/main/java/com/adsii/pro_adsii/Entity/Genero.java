    package com.adsii.pro_adsii.Entity;

    import jakarta.persistence.*;

    @Entity
    @Table(name = "Genero")
    public class Genero {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id_genero")
        private Long idGenero;

        @Column(name = "nombre", nullable = false, length = 50)
        private String nombre;

        // Constructor vacío
        public Genero() {
        }

        // Constructor completo
        public Genero(Long idGenero, String nombre) {
            this.idGenero = idGenero;
            this.nombre = nombre;
        }

        // Getters y Setters
        public Long getIdGenero() {
            return idGenero;
        }

        public void setIdGenero(Long idGenero) {
            this.idGenero = idGenero;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

    }

