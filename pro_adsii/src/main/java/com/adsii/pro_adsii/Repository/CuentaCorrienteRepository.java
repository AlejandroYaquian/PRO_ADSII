package com.adsii.pro_adsii.Repository;

import com.adsii.pro_adsii.Entity.CuentaCorriente;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaCorrienteRepository extends JpaRepository<CuentaCorriente, Integer> {
    
    // Buscar cuentas por ID de Persona (cliente)
    List<CuentaCorriente> findByPersona_IdPersona(int idPersona);
    
    // Buscar cuenta por número de cuenta
    CuentaCorriente findByNumeroCuenta(String numeroCuenta);
}