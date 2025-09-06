package com.adsii.pro_adsii.DTO;

public class OpcionDTO {

    private String modulo;
    private String menu;
    private Integer idRole;
    private Integer idOpcion;
    private String nombre;
    private Boolean alta;
    private Boolean baja;
    private Boolean imprimir;
    private Boolean exportar;
    
    
    public String getModulo() {
        return modulo;
    }
    public void setModulo(String modulo) {
        this.modulo = modulo;
    }
    public String getMenu() {
        return menu;
    }
    public void setMenu(String menu) {
        this.menu = menu;
    }
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
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getAlta() {
        return alta;
    }
    public void setAlta(Boolean alta) {
        this.alta = alta;
    }
    public Boolean getBaja() {
        return baja;
    }
    public void setBaja(Boolean baja) {
        this.baja = baja;
    }
    public Boolean getImprimir() {
        return imprimir;
    }
    public void setImprimir(Boolean imprimir) {
        this.imprimir = imprimir;
    }
    public Boolean getExportar() {
        return exportar;
    }
    public void setExportar(Boolean exportar) {
        this.exportar = exportar;
    }
   

}
