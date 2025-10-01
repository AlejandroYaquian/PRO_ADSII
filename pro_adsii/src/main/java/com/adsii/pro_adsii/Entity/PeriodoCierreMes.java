package com.adsii.pro_adsii.Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "PERIODO_CIERRE_MES")
@IdClass(PeriodoCierreMes.PK.class)
public class PeriodoCierreMes {

    @Id
    @Column(name = "Anio")
    private Integer anio;

    @Id
    @Column(name = "Mes")
    private Integer mes;

    @Column(name = "FechaInicio")
    private LocalDateTime fechaInicio;

    @Column(name = "FechaFinal")
    private LocalDateTime fechaFinal;

    @Column(name = "FechaCierre")
    private LocalDateTime fechaCierre;

    @Column(name = "FechaCreacion")
    private LocalDateTime fechaCreacion;

    @Column(name = "UsuarioCreacion")
    private String usuarioCreacion;

    @Column(name = "FechaModificacion")
    private LocalDateTime fechaModificacion;

    @Column(name = "UsuarioModificacion")
    private String usuarioModificacion;


    public Integer getAnio() { return anio; }
    public void setAnio(Integer anio) { this.anio = anio; }

    public Integer getMes() { return mes; }
    public void setMes(Integer mes) { this.mes = mes; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDateTime getFechaFinal() { return fechaFinal; }
    public void setFechaFinal(LocalDateTime fechaFinal) { this.fechaFinal = fechaFinal; }

    public LocalDateTime getFechaCierre() { return fechaCierre; }
    public void setFechaCierre(LocalDateTime fechaCierre) { this.fechaCierre = fechaCierre; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getUsuarioCreacion() { return usuarioCreacion; }
    public void setUsuarioCreacion(String usuarioCreacion) { this.usuarioCreacion = usuarioCreacion; }

    public LocalDateTime getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(LocalDateTime fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    public String getUsuarioModificacion() { return usuarioModificacion; }
    public void setUsuarioModificacion(String usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }

    //  PK
    public static class PK implements Serializable {
        private Integer anio;
        private Integer mes;

        public PK() {}
        public PK(Integer anio, Integer mes) { this.anio = anio; this.mes = mes; }

        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PK pk)) return false;
            return Objects.equals(anio, pk.anio) && Objects.equals(mes, pk.mes);
        }
        @Override public int hashCode() { return Objects.hash(anio, mes); }
    }
}
