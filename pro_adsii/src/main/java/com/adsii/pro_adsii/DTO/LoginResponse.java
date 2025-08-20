package com.adsii.pro_adsii.DTO;

public class LoginResponse {
    private boolean success;
    private String mensaje;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    
    public LoginResponse(boolean success, String mensaje) {
        this.success = success;
        this.mensaje = mensaje;
    }
}