package com.adsii.pro_adsii.DTO;

public class LoginResponse {
    private boolean success;
    private String mensaje;
    private String idUsuario;

    public LoginResponse(boolean success, String mensaje, String idUsuario) {
        this.success = success;
        this.mensaje = mensaje;
        this.idUsuario = idUsuario;
    }

    // getters y setters
    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMensaje() { return mensaje; }
    public void setMensaje(String mensaje) { this.mensaje = mensaje; }

    public String getIdUsuario() { return idUsuario; }
    public void setIdUsuario(String idUsuario) { this.idUsuario = idUsuario; }
}
