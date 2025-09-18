package com.adsii.pro_adsii.DTO;

public class LoginResponse {
    private boolean success;
    private String mensaje;
    private String idUsuario;
    private Integer idRole;   // solo el id del rol

    public LoginResponse(boolean success, String mensaje, String idUsuario, Integer idRole) {
        this.success = success;
        this.mensaje = mensaje;
        this.idUsuario = idUsuario;
        this.idRole = idRole;
    }

    // getters y setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }

    public Integer getIdRole() { return idRole; }
    public void setIdRole(Integer idRole) { this.idRole = idRole; }
}