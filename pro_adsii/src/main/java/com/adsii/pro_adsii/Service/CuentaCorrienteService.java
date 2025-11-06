package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.CuentaCorriente;
import com.adsii.pro_adsii.Entity.Persona; 
import com.adsii.pro_adsii.Entity.TipoSaldoCuenta;
import com.adsii.pro_adsii.Entity.StatusCuenta;
import com.adsii.pro_adsii.Repository.CuentaCorrienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaCorrienteService {

    @Autowired
    private CuentaCorrienteRepository repository;
    
    // Servicios auxiliares
    @Autowired
    private PersonaService personaService; 
    @Autowired
    private TipoSaldoCuentaService tipoSaldoService;
    @Autowired
    private StatusCuentaService statusCuentaService;

    public List<CuentaCorriente> listarTodas() {
        return repository.findAll();
    }

    public CuentaCorriente obtenerPorId(Integer id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Cuenta con ID " + id + " no encontrada."));
    }
    
    public List<CuentaCorriente> buscarPorIdPersona(int idPersona) {
        return repository.findByPersona_IdPersona(idPersona);
    }
    
    public CuentaCorriente buscarPorNumeroCuenta(String numeroCuenta) {
        return repository.findByNumeroCuenta(numeroCuenta);
    }

    public CuentaCorriente guardar(CuentaCorriente cuenta, String usuarioActual) {
        
        // *****************************************************************
        // 1. Lógica para CARGAR las entidades relacionadas antes de guardar
        // *****************************************************************
        
        // Cargar Persona (Cliente) - Servicio espera 'int'
        Integer idPersona = Optional.ofNullable(cuenta.getPersona())
                            .map(Persona::getIdPersona)
                            .orElseThrow(() -> new IllegalArgumentException("ID de Persona (Cliente) es obligatorio."));
        Persona persona = personaService.obtenerPorId(idPersona); 
        cuenta.setPersona(persona);

        // Cargar TipoSaldoCuenta - Servicio espera 'Long' (REQUIERE CONVERSIÓN)
        Long idTipoSaldoInt = Optional.ofNullable(cuenta.getTipoSaldoCuenta())
                                 .map(TipoSaldoCuenta::getIdTipoSaldoCuenta)
                                 .orElseThrow(() -> new IllegalArgumentException("ID de Tipo de Saldo es obligatorio."));
        
        // CORRECCIÓN para TipoSaldoCuentaService: Convertir a Long
        Long idTipoSaldoLong = idTipoSaldoInt.longValue(); 
        TipoSaldoCuenta tipoSaldo = tipoSaldoService.obtenerPorId(idTipoSaldoLong);
        cuenta.setTipoSaldoCuenta(tipoSaldo);
        
        // Cargar StatusCuenta - Servicio espera 'Integer' (NO REQUIERE CONVERSIÓN)
        Integer idStatusInt = Optional.ofNullable(cuenta.getStatusCuenta())
                              .map(StatusCuenta::getIdStatusCuenta)
                              .orElseThrow(() -> new IllegalArgumentException("ID de Estado de Cuenta es obligatorio."));
        
        // CORRECCIÓN para StatusCuentaService: Usar Integer directamente
        StatusCuenta status = statusCuentaService.obtenerPorId(idStatusInt);
        cuenta.setStatusCuenta(status);
        
        // *****************************************************************
        // 2. Lógica de auditoría (Creación/Modificación)
        // *****************************************************************
        if (cuenta.getIdSaldoCuenta() == null || cuenta.getIdSaldoCuenta() == 0) {
            // CREAR
            cuenta.setFechaCreacion(LocalDateTime.now());
            cuenta.setUsuarioCreacion(usuarioActual);
            
            if (cuenta.getFechaApertura() == null) {
                cuenta.setFechaApertura(LocalDateTime.now()); 
            }
            cuenta.setFechaModificacion(null);
            cuenta.setUsuarioModificacion(null);
        } else {
            // ACTUALIZAR
            CuentaCorriente existente = repository.findById(cuenta.getIdSaldoCuenta())
                .orElseThrow(() -> new EntityNotFoundException("Cuenta con ID " + cuenta.getIdSaldoCuenta() + " no encontrada"));
            
            // Preservar la información de creación original
            cuenta.setFechaCreacion(existente.getFechaCreacion());
            cuenta.setUsuarioCreacion(existente.getUsuarioCreacion());
            
            // Establecer la información de modificación
            cuenta.setFechaModificacion(LocalDateTime.now());
            cuenta.setUsuarioModificacion(usuarioActual);
        }
        
        return repository.save(cuenta);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}