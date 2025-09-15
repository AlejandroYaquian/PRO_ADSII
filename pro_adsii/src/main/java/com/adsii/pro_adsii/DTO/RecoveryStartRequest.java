package com.adsii.pro_adsii.DTO;

import java.util.List;

public class RecoveryStartRequest {
    public static class QuestionDTO {
    private Long id;     
    private String texto;

    public QuestionDTO() {}
    public QuestionDTO(Long id, String texto) { this.id = id; this.texto = texto; }
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
  }

  private boolean success;
  private String mensaje;
  private Long usuarioId;
  private List<QuestionDTO> preguntas;

  public boolean isSuccess() { return success; }
  public void setSuccess(boolean success) { this.success = success; }
  public String getMensaje() { return mensaje; }
  public void setMensaje(String mensaje) { this.mensaje = mensaje; }
  public Long getUsuarioId() { return usuarioId; }
  public void setUsuarioId(Long usuarioId) { this.usuarioId = usuarioId; }
  public List<QuestionDTO> getPreguntas() { return preguntas; }
  public void setPreguntas(List<QuestionDTO> preguntas) { this.preguntas = preguntas; }
}