package com.adsii.pro_adsii.Service;

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
         //Obtengo el objeto TipoMovimiento por medio del ID
         Optional<TipoMovimientoCXC> tipoMov = tipoMovimientoCXCRepository.findById(movimientoNuevo.getTipoMovimientoCXC().getIdTipoMovimientoCXC());
         TipoMovimientoCXC tipoMovimiento = tipoMov.get();
         movimientoNuevo.setTipoMovimientoCXC(tipoMovimiento);

         //Obtengo el objeto Saldo cuenta por medio del ID
         Optional<SaldoCuenta> saldo = saldoCuentaRepository.findById(movimientoNuevo.getSaldoCuenta().getIdSaldoCuenta());
         SaldoCuenta saldoCuenta = saldo.get();
         movimientoNuevo.setSaldoCuenta(saldoCuenta);
         movimientoNuevo.setFechaCreacion(fecha);
         movimientoNuevo.setGeneradoAutomaticamente(false);
         return movimientoCuentaRepository.save(movimientoNuevo);
     }

}
