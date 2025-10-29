package com.adsii.pro_adsii.Repository;

import com.adsii.pro_adsii.DTO.EstadoCuentaDTO;
import com.adsii.pro_adsii.Entity.MovimientoCuenta;
import org.springframework.data.jpa.repository.JpaRepository; // Usamos JpaRepository
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository; // Cambiamos @Component a @Repository

import java.util.List;
import java.time.LocalDateTime;

@Repository
// Extiende JpaRepository con la entidad principal (MovimientoCuenta) y el tipo de su ID (Integer).
public interface EstadoCuentaRepository extends JpaRepository<MovimientoCuenta, Integer> {

    @Query("""
        SELECT new com.adsii.pro_adsii.DTO.EstadoCuentaDTO(
            p.idPersona, p.nombre, p.apellido,
            sc.idSaldoCuenta, tsc.nombre,
            mc.fechaMovimiento, tmc.nombre,
            mc.descripcion,
            -- Determina si es Cargo (operacion = 1) o 0
            CASE WHEN tmc.operacionCuentaCorriente = 1 THEN mc.valorMovimiento ELSE 0 END,
            -- Determina si es Abono (operacion = -1) o 0
            CASE WHEN tmc.operacionCuentaCorriente = -1 THEN mc.valorMovimiento ELSE 0 END,
            0.0 -- Inicializa saldoAcumulado en 0.0 (se calcula en el Service)
        )
        FROM MovimientoCuenta mc
        JOIN mc.saldoCuenta sc
        JOIN sc.persona p
        JOIN sc.tipoSaldoCuenta tsc
        JOIN mc.tipoMovimiento tmc
        WHERE (:idPersona IS NULL OR p.idPersona = :idPersona)
          AND (:idSaldoCuenta IS NULL OR sc.idSaldoCuenta = :idSaldoCuenta)
          AND (LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%')))
          AND (LOWER(p.apellido) LIKE LOWER(CONCAT('%', :apellido, '%')))
          AND (mc.fechaMovimiento >= :fechaInicio)
          AND (mc.fechaMovimiento <= :fechaFin)
        ORDER BY mc.fechaMovimiento ASC
    """)
    List<EstadoCuentaDTO> buscarEstadoCuenta(
        Integer idPersona, 
        Integer idSaldoCuenta, 
        String nombre, 
        String apellido,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin
    );
}