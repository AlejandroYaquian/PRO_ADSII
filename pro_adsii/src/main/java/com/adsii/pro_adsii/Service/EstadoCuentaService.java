package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.DTO.EstadoCuentaDTO;
import com.adsii.pro_adsii.Repository.EstadoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EstadoCuentaService {

    @Autowired
    private EstadoCuentaRepository estadoCuentaRepository;

    /**
     * Busca y calcula el estado de cuenta.
     */
    public List<EstadoCuentaDTO> buscar(
        Integer idPersona, 
        Integer idSaldoCuenta, 
        String nombre, 
        String apellido,
        LocalDateTime fechaInicio,
        LocalDateTime fechaFin
    ) {
        // 1. Obtener la lista de movimientos desde el repositorio (ya ordenados por fecha)
        List<EstadoCuentaDTO> movimientos = estadoCuentaRepository.buscarEstadoCuenta(
            idPersona, idSaldoCuenta, nombre, apellido, fechaInicio, fechaFin
        );

        // 2. Calcular el Saldo Acumulado
        double saldoCorriente = 0.0;
        
        // CÁLCULO CRÍTICO:
        // Asumo que 'Cargo' es un aumento a la deuda (suma al saldo) y 'Abono' es un pago (resta al saldo),
        // típico de una Cuenta por Cobrar (CXC).
        // Si fuera una Cuenta de Ahorro/Bancaria: saldoCorriente = saldoCorriente + dto.abono - dto.cargo;

        for (EstadoCuentaDTO dto : movimientos) {
            saldoCorriente = saldoCorriente + dto.cargo - dto.abono;
            dto.saldoAcumulado = saldoCorriente;
        }

        return movimientos;
    }

    /**
     * Lógica para el Cierre de Mes (Funcionalidad avanzada).
     * Esto podría implicar:
     * 1. Calcular intereses/mora del periodo.
     * 2. Generar un registro de "Balance de Apertura" para el siguiente mes.
     * 3. Marcar el periodo como cerrado.
     */
    public void realizarCierreDeMes(Integer idSaldoCuenta, LocalDateTime fechaCierre) {
        // Lógica de negocio para cierre de mes:
        // 1. Obtener movimientos del periodo anterior.
        // 2. Calcular saldos finales, intereses, etc.
        // 3. Persistir un nuevo registro de "saldo inicial del mes" en MovimientoCuenta (o una tabla de balances).
        
        // Ejemplo simplificado:
        // System.out.println("Realizando cierre para la cuenta " + idSaldoCuenta + " al " + fechaCierre);
        // Aquí iría la lógica transaccional para el cierre.
    }
}