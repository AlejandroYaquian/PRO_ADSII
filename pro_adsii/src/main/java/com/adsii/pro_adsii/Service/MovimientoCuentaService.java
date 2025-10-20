package com.adsii.pro_adsii.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.Entity.MovimientoCuenta;
import com.adsii.pro_adsii.Entity.SaldoCuenta;
import com.adsii.pro_adsii.Entity.TipoMovimientoCXC;
import com.adsii.pro_adsii.Repository.MovimientoCuentaRepository;
import com.adsii.pro_adsii.Repository.SaldoCuentaRepository;
import com.adsii.pro_adsii.Repository.TipoMovimientoCXCRepository;

@Service
public class MovimientoCuentaService {

     @Autowired
     MovimientoCuentaRepository movimientoCuentaRepository;

     @Autowired
     private SaldoCuentaRepository saldoCuentaRepository;

     @Autowired
     private TipoMovimientoCXCRepository tipoMovimientoCXCRepository;

  public MovimientoCuenta guardarMovimientoCuenta(MovimientoCuenta movimientoNuevo) {
    Date fecha = new Date();

    Optional<SaldoCuenta> saldo = saldoCuentaRepository.findById(movimientoNuevo.getSaldoCuenta().getIdSaldoCuenta());
    SaldoCuenta saldoCuenta = saldo.orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));

    Optional<TipoMovimientoCXC> tipoMov = tipoMovimientoCXCRepository.findById(movimientoNuevo.getTipoMovimientoCXC().getIdTipoMovimientoCXC());
    TipoMovimientoCXC tipoMovimiento = tipoMov.orElseThrow(() -> new RuntimeException("Tipo de movimiento no encontrado"));

    if(tipoMovimiento.getOperacionCuentaCorriente() == 1){ // cargo
        double saldoActual = (saldoCuenta.getSaldoAnterior().doubleValue()+saldoCuenta.getCreditos().doubleValue()-saldoCuenta.getDebitos().doubleValue());
        if(saldoActual < movimientoNuevo.getValorMovimiento()){
            // Lanzar excepción para indicar saldo insuficiente
            throw new RuntimeException("Saldo insuficiente para realizar el movimiento");
        } else {
            movimientoNuevo.setValorMovimientoPagado(movimientoNuevo.getValorMovimiento()); 
            BigDecimal valor = new BigDecimal(String.valueOf(movimientoNuevo.getValorMovimiento()));
            saldoCuenta.setDebitos(
            saldoCuenta.getDebitos().add(valor));
            movimientoNuevo.setValorMovimiento(0.0);

        }    
    } else {
        movimientoNuevo.setValorMovimientoPagado(0.0); 
        movimientoNuevo.setValorMovimiento(movimientoNuevo.getValorMovimiento());
        BigDecimal valor = new BigDecimal(String.valueOf(movimientoNuevo.getValorMovimiento()));
        saldoCuenta.setCreditos(
        saldoCuenta.getCreditos().add(valor));
    }  

    movimientoNuevo.setTipoMovimientoCXC(tipoMovimiento);
    movimientoNuevo.setSaldoCuenta(saldoCuenta);
    movimientoNuevo.setFechaCreacion(fecha);
    movimientoNuevo.setGeneradoAutomaticamente(false);

    return movimientoCuentaRepository.save(movimientoNuevo);
}


}

