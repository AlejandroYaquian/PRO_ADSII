package com.adsii.pro_adsii.DTO;

public class RoleDTO {
    private String nombre;
    private int idRole;

    public RoleDTO() {}

    public RoleDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
