package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.CuentaCorriente;
import com.adsii.pro_adsii.Repository.CuentaCorrienteRepository;
import com.adsii.pro_adsii.Repository.PersonaRepository;
import com.adsii.pro_adsii.Repository.StatusCuentaRepository;
import com.adsii.pro_adsii.Repository.TipoSaldoCuentaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;


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

    // Listar todas las cuentas
    public List<CuentaCorriente> listar() {
        return repo.findAll();
    }

    // Buscar por persona
    public List<CuentaCorriente> listarPorPersona(Integer idPersona) {
        return repo.findByPersona_IdPersona(idPersona);
    }

    // Buscar una cuenta específica
    public CuentaCorriente obtenerPorId(Integer id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    // Crear cuenta - CORREGIDO PARA CONVERSIÓN DE IDs A ENTIDADES
    public CuentaCorriente crear(CuentaCorriente cuenta, String usuario) {
        if (repo.existsByNumeroCuenta(cuenta.getNumeroCuenta())) {
            throw new RuntimeException("Ya existe una cuenta con ese número");
        }
        
        // Asignación de Persona
        Integer idPersona = cuenta.getPersona() != null ? cuenta.getPersona().getIdPersona() : null;
        if (idPersona == null) {
            throw new RuntimeException("ID de Persona (Cliente) es requerido");
        }
        cuenta.setPersona(personaRepo.getReferenceById(idPersona));

        // Asignación de TipoSaldoCuenta
        Long idTipo = cuenta.getTipoSaldoCuenta() != null ? cuenta.getTipoSaldoCuenta().getIdTipoSaldoCuenta() : null;
        if (idTipo == null) {
             throw new RuntimeException("ID de Tipo de Saldo es requerido");
        }
        cuenta.setTipoSaldoCuenta(tipoRepo.getReferenceById(idTipo));

        // Asignación de StatusCuenta
        Integer idStatus = cuenta.getStatusCuenta() != null ? cuenta.getStatusCuenta().getIdStatusCuenta() : null;
        if (idStatus == null) {
            throw new RuntimeException("ID de Status de Cuenta es requerido");
        }
        cuenta.setStatusCuenta(statusRepo.getReferenceById(idStatus));

        cuenta.setFechaApertura(LocalDateTime.now());
        cuenta.setFechaCreacion(LocalDateTime.now());
        cuenta.setUsuarioCreacion(usuario);
        
        return repo.save(cuenta);
    }

    // Actualizar cuenta - CORREGIDO PARA PERSISTIR CAMBIOS EN RELACIONES
    public CuentaCorriente actualizar(Integer id, CuentaCorriente cuentaNueva, String usuario) {
        CuentaCorriente cuenta = obtenerPorId(id);

        // Actualizar solo campos modificables, manteniendo las relaciones
        cuenta.setSaldoAnterior(cuentaNueva.getSaldoAnterior());
        cuenta.setDebitos(cuentaNueva.getDebitos());
        cuenta.setCreditos(cuentaNueva.getCreditos());

        // Aseguramos que los objetos de relación se actualicen usando getReferenceById si el ID cambia
        Integer idStatus = cuentaNueva.getStatusCuenta() != null ? cuentaNueva.getStatusCuenta().getIdStatusCuenta() : null;
        if (idStatus != null) {
             cuenta.setStatusCuenta(statusRepo.getReferenceById(idStatus));
        }

        Long idTipo = cuentaNueva.getTipoSaldoCuenta() != null ? cuentaNueva.getTipoSaldoCuenta().getIdTipoSaldoCuenta() : null;
        if (idTipo != null) {
            cuenta.setTipoSaldoCuenta(tipoRepo.getReferenceById(idTipo));
        }
        
        cuenta.setFechaModificacion(LocalDateTime.now());
        cuenta.setUsuarioModificacion(usuario);

        return repo.save(cuenta);
    }

    // Eliminar cuenta
    public void eliminar(Integer id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Cuenta no encontrada");
        }
        repo.deleteById(id);
    }
}