package com.adsii.pro_adsii.DTO;

public class RecoveryStartResponse {
    
 private boolean success;
    private String pregunta;

    public RecoveryStartResponse() {}
    public RecoveryStartResponse(boolean success, String pregunta) {
        this.success = success; this.pregunta = pregunta;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }
    public String getPregunta() { return pregunta; }
    public void setPregunta(String pregunta) { this.pregunta = pregunta; }
}
