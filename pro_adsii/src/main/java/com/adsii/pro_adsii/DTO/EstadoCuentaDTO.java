package com.adsii.pro_adsii.DTO;

import java.time.LocalDateTime;

public class EstadoCuentaDTO {
    // Datos del cliente y cuenta
    public int idPersona;
    public String nombre;
    public String apellido;
    public int idSaldoCuenta;
    public String tipoSaldo;

    // Datos del movimiento
    public LocalDateTime fechaMovimiento;
    public String tipoMovimiento;
    public String descripcion; // Documento de referencia/Descripción
    public double cargo;
    public double abono;
    public double saldoAcumulado; // Se calcula en el Service

    // Constructor usado en la consulta JPQL del Repository
    public EstadoCuentaDTO(
        int idPersona, String nombre, String apellido,
        int idSaldoCuenta, String tipoSaldo,
        LocalDateTime fechaMovimiento, String tipoMovimiento,
        String descripcion, double cargo, double abono, double saldoAcumulado
    ) {
        this.idPersona = idPersona;
        this.nombre = nombre;
        this.apellido = apellido;
        this.idSaldoCuenta = idSaldoCuenta;
        this.tipoSaldo = tipoSaldo;
        this.fechaMovimiento = fechaMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.descripcion = descripcion;
        this.cargo = cargo;
        this.abono = abono;
        this.saldoAcumulado = saldoAcumulado;
    }
    
    public EstadoCuentaDTO() {
    }
}