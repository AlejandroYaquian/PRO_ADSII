package com.adsii.pro_adsii.Entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "SALDO_CUENTA_HIST")
@IdClass(SaldoCuentaHist.PK.class)
public class SaldoCuentaHist {

    @Id
    @Column(name = "Anio")
    private Integer anio;

    @Id
    @Column(name = "Mes")
    private Integer mes;

    @Id
    @Column(name = "idSaldoCuenta")
    private Integer idSaldoCuenta;

    @Column(name = "idPersona")
    private Integer idPersona;

    @Column(name = "idTipoSaldoCuenta")
    private Integer idTipoSaldoCuenta;

    @Column(name = "SaldoAnterior")
    private BigDecimal saldoAnterior;

    @Column(name = "Debitos")
    private BigDecimal debitos;

    @Column(name = "Creditos")
    private BigDecimal creditos;

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

    public Integer getIdSaldoCuenta() { return idSaldoCuenta; }
    public void setIdSaldoCuenta(Integer idSaldoCuenta) { this.idSaldoCuenta = idSaldoCuenta; }

    public Integer getIdPersona() { return idPersona; }
    public void setIdPersona(Integer idPersona) { this.idPersona = idPersona; }

    public Integer getIdTipoSaldoCuenta() { return idTipoSaldoCuenta; }
    public void setIdTipoSaldoCuenta(Integer idTipoSaldoCuenta) { this.idTipoSaldoCuenta = idTipoSaldoCuenta; }

    public BigDecimal getSaldoAnterior() { return saldoAnterior; }
    public void setSaldoAnterior(BigDecimal saldoAnterior) { this.saldoAnterior = saldoAnterior; }

    public BigDecimal getDebitos() { return debitos; }
    public void setDebitos(BigDecimal debitos) { this.debitos = debitos; }

    public BigDecimal getCreditos() { return creditos; }
    public void setCreditos(BigDecimal creditos) { this.creditos = creditos; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getUsuarioCreacion() { return usuarioCreacion; }
    public void setUsuarioCreacion(String usuarioCreacion) { this.usuarioCreacion = usuarioCreacion; }

    public LocalDateTime getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(LocalDateTime fechaModificacion) { this.fechaModificacion = fechaModificacion; }
    
    public String getUsuarioModificacion() { return usuarioModificacion; }
    public void setUsuarioModificacion(String usuarioModificacion) { this.usuarioModificacion = usuarioModificacion; }

    // PK
    public static class PK implements Serializable {
        private Integer anio;
        private Integer mes;
        private Integer idSaldoCuenta;

        public PK() {}
        public PK(Integer anio, Integer mes, Integer idSaldoCuenta) {
            this.anio = anio; this.mes = mes; this.idSaldoCuenta = idSaldoCuenta;
        }
        @Override public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof PK pk)) return false;
            return Objects.equals(anio, pk.anio)
                && Objects.equals(mes, pk.mes)
                && Objects.equals(idSaldoCuenta, pk.idSaldoCuenta);
        }
        @Override public int hashCode() { return Objects.hash(anio, mes, idSaldoCuenta); }
    }
}
