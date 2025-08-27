package com.adsii.pro_adsii.Entity;

import java.io.Serializable;
import java.util.Objects;
import jakarta.persistence.*;

@Embeddable
public class RoleOpcionId implements Serializable {

    @Column(name = "IdRole")
    private Integer idRole;

    @Column(name = "IdOpcion")
    private Integer idOpcion;

    public RoleOpcionId() {}

    public RoleOpcionId(Integer idRole, Integer idOpcion) {
        this.idRole = idRole;
        this.idOpcion = idOpcion;
    }

    // Getters y setters

    public Integer getIdRole() {
        return idRole;
    }

    public void setIdRole(Integer idRole) {
        this.idRole = idRole;
    }

    public Integer getIdOpcion() {
        return idOpcion;
    }

    public void setIdOpcion(Integer idOpcion) {
        this.idOpcion = idOpcion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RoleOpcionId)) return false;
        RoleOpcionId that = (RoleOpcionId) o;
        return Objects.equals(idRole, that.idRole) &&
               Objects.equals(idOpcion, that.idOpcion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRole, idOpcion);
    }
}
