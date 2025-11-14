package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.CuentaCorriente;
import com.adsii.pro_adsii.Repository.CuentaCorrienteRepository;
import com.adsii.pro_adsii.Repository.PersonaRepository;
import com.adsii.pro_adsii.Repository.StatusCuentaRepository;
import com.adsii.pro_adsii.Repository.TipoSaldoCuentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional; 
import java.time.LocalDateTime;
import java.util.List;
import java.math.BigDecimal;


@Service
public class CuentaCorrienteService {

    @Autowired
    private CuentaCorrienteRepository repo;

    @Autowired
    private PersonaRepository personaRepo;

    @Autowired
    private TipoSaldoCuentaRepository tipoRepo;

    @Autowired
    private StatusCuentaRepository statusRepo;

    public List<CuentaCorriente> listar() {
        return repo.findAll();
    }

    public List<CuentaCorriente> listarPorPersona(Integer idPersona) {
        return repo.findByPersona_IdPersona(idPersona);
    }

    public CuentaCorriente obtenerPorId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }
    
    // ===============================================
    //           IMPLEMENTACIÓN DE CREACIÓN (Final)
    // ===============================================
    @Transactional // Permite guardar 2 veces para asignar el ID como NumeroCuenta
    public CuentaCorriente crear(CuentaCorriente cuenta, String usuario) {
        // Asignación de Entidades relacionadas (validaciones)
        Integer idPersona = cuenta.getPersona() != null ? cuenta.getPersona().getIdPersona() : null;
        if (idPersona == null) {
            throw new RuntimeException("ID de Persona (Cliente) es requerido");
        }
        cuenta.setPersona(personaRepo.getReferenceById(idPersona)); 

        Long idTipo = cuenta.getTipoSaldoCuenta() != null ? cuenta.getTipoSaldoCuenta().getIdTipoSaldoCuenta() : null;
        if (idTipo == null) {
             throw new RuntimeException("ID de Tipo de Saldo es requerido");
        }
        cuenta.setTipoSaldoCuenta(tipoRepo.getReferenceById(idTipo));

        Integer idStatus = cuenta.getStatusCuenta() != null ? cuenta.getStatusCuenta().getIdStatusCuenta() : null;
        if (idStatus == null) {
            throw new RuntimeException("ID de Status de Cuenta es requerido");
        }
        cuenta.setStatusCuenta(statusRepo.getReferenceById(idStatus));
        
        // Gestión de saldos 
        if (cuenta.getSaldoAnterior() == null) cuenta.setSaldoAnterior(BigDecimal.ZERO);
        if (cuenta.getDebitos() == null) cuenta.setDebitos(BigDecimal.ZERO);
        if (cuenta.getCreditos() == null) cuenta.setCreditos(BigDecimal.ZERO);

        // IMPLEMENTACIÓN DE AUDITORÍA
        if (cuenta.getFechaApertura() == null) {
             cuenta.setFechaApertura(LocalDateTime.now());
        }
        cuenta.setFechaCreacion(LocalDateTime.now());
        cuenta.setUsuarioCreacion(usuario); 
        
        // REGLA CLAVE: Si numeroCuenta es NOT NULL en la BD y se genera con el ID,
        // inicializamos temporalmente para el primer save().
        if (cuenta.getNumeroCuenta() == null) {
            cuenta.setNumeroCuenta("0"); // Valor temporal para pasar la restricción NOT NULL
        }
        
        // 1. Guardar para obtener el ID (IdSaldoCuenta)
        CuentaCorriente cuentaGuardada = repo.save(cuenta);

        // 2. Asignar el ID como Número de Cuenta (Cumpliendo la regla de unicidad y contenido)
        if (cuentaGuardada.getIdSaldoCuenta() != null) {
            String nuevoNumeroCuenta = String.valueOf(cuentaGuardada.getIdSaldoCuenta());
            cuentaGuardada.setNumeroCuenta(nuevoNumeroCuenta);
            // 3. Guardar de nuevo para persistir el numero_cuenta final
            repo.save(cuentaGuardada); 
        }

        return cuentaGuardada;
    }

    // ===============================================
    //           IMPLEMENTACIÓN DE ACTUALIZACIÓN
    // ===============================================
    public CuentaCorriente actualizar(Integer id, CuentaCorriente cuentaNueva, String usuario) {
        CuentaCorriente cuentaExistente = obtenerPorId(id);

        // Actualizar campos de saldo 
        if (cuentaNueva.getSaldoAnterior() != null) cuentaExistente.setSaldoAnterior(cuentaNueva.getSaldoAnterior());
        if (cuentaNueva.getDebitos() != null) cuentaExistente.setDebitos(cuentaNueva.getDebitos());
        if (cuentaNueva.getCreditos() != null) cuentaExistente.setCreditos(cuentaNueva.getCreditos());

        // Actualizar StatusCuenta si se envía
        Integer idStatus = cuentaNueva.getStatusCuenta() != null ? cuentaNueva.getStatusCuenta().getIdStatusCuenta() : null;
        if (idStatus != null) {
             cuentaExistente.setStatusCuenta(statusRepo.getReferenceById(idStatus));
        }

        // Actualizar TipoSaldoCuenta si se envía
        Long idTipo = cuentaNueva.getTipoSaldoCuenta() != null ? cuentaNueva.getTipoSaldoCuenta().getIdTipoSaldoCuenta() : null;
        if (idTipo != null) {
            cuentaExistente.setTipoSaldoCuenta(tipoRepo.getReferenceById(idTipo));
        }
        
        // IMPLEMENTACIÓN DE AUDITORÍA (MODIFICACIÓN)
        cuentaExistente.setFechaModificacion(LocalDateTime.now());
        cuentaExistente.setUsuarioModificacion(usuario); 
        
        return repo.save(cuentaExistente);
    }

    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Cuenta no encontrada");
        }
        repo.deleteById(id);
    }
}