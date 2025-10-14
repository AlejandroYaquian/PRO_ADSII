package com.adsii.pro_adsii.Repository;

import com.adsii.pro_adsii.Entity.SaldoCuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SaldoCuentaRepository extends JpaRepository<SaldoCuenta, Integer> {
    List<SaldoCuenta> findByIdSaldoCuenta(int idSaldoCuenta);
    List<SaldoCuenta> findByPersona_IdPersona(int idPersona);
    List<SaldoCuenta> findByPersona_NombreContainingIgnoreCaseAndPersona_ApellidoContainingIgnoreCase(String nombre, String apellido);
}
