package com.adsii.pro_adsii.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.adsii.pro_adsii.Entity.TipoMovimientoCXC;
import com.adsii.pro_adsii.Repository.TipoMovimientoCXCRepository;

@Service
public class TipoMovimientoCXCService {

    @Autowired
    TipoMovimientoCXCRepository tipoMovCXCRepository;

     public TipoMovimientoCXC buscarTipoMovimiento(int idTipoMovimiento) {
        Optional<TipoMovimientoCXC> tipoMovimientoCXC = tipoMovCXCRepository.findById(idTipoMovimiento);	
		return tipoMovimientoCXC.isPresent() ? tipoMovimientoCXC.get() : null;
	}


   public TipoMovimientoCXC guardarTipoMovimiento(TipoMovimientoCXC movimientoNuevo) {
    if (movimientoNuevo.getIdTipoMovimientoCXC() == null) {
        // Creación
        movimientoNuevo.setFechaCreacion(new Date());
        return tipoMovCXCRepository.save(movimientoNuevo);
    } else {
        // Edición: buscar el registro existente
        Optional<TipoMovimientoCXC> existente = tipoMovCXCRepository.findById(movimientoNuevo.getIdTipoMovimientoCXC());
        if (existente.isPresent()) {
            TipoMovimientoCXC existenteEntity = existente.get();
            existenteEntity.setNombre(movimientoNuevo.getNombre());
            existenteEntity.setOperacionCuentaCorriente(movimientoNuevo.getOperacionCuentaCorriente());
            existenteEntity.setFechaModificacion(new Date());
            existenteEntity.setUsuarioModificacion(movimientoNuevo.getUsuarioModificacion());
            return tipoMovCXCRepository.save(existenteEntity);
        } else {
            throw new RuntimeException("No se encontró el tipo de movimiento con ID: " + movimientoNuevo.getIdTipoMovimientoCXC());
        }
    }
}



    public List<TipoMovimientoCXC> obtenerTipoMovimientosCXC(){
        return tipoMovCXCRepository.findAll();
    }

    
     public void editarTipoMovCXC(TipoMovimientoCXC tipoMovimientoCXC){
        Optional<TipoMovimientoCXC> tipMov= tipoMovCXCRepository.findById(tipoMovimientoCXC.getIdTipoMovimientoCXC());
        Date fecha = new Date();
        TipoMovimientoCXC tipoMovimiento = tipMov.get();
        tipoMovimiento.setNombre(tipoMovimientoCXC.getNombre());
        tipoMovimiento.setOperacionCuentaCorriente(tipoMovimientoCXC.getOperacionCuentaCorriente());
        tipoMovimiento.setFechaModificacion(fecha);
        tipoMovimiento.setUsuarioModificacion(tipoMovimientoCXC.getUsuarioModificacion());
        tipoMovCXCRepository.save(tipoMovimiento);
    }

    public void eliminarTipoMovCXC(Integer idTipoMov) {
        TipoMovimientoCXC tipoMovimientoCXC = tipoMovCXCRepository.findById(idTipoMov)
            .orElseThrow(() -> new RuntimeException("Rol no encontrado con ID: " + idTipoMov));
       /*  if (tipoMovimientoCXC.getUsuarios() != null && !role.getUsuarios().isEmpty()) {
            throw new RuntimeException("No se puede eliminar el rol porque tiene usuarios vinculados.");
        }*/
        tipoMovCXCRepository.delete(tipoMovimientoCXC);
    }


}
