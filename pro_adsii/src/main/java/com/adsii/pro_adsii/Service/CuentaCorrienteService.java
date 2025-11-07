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

    // Crear cuenta
    public CuentaCorriente crear(CuentaCorriente cuenta, String usuario) {
        if (repo.existsByNumeroCuenta(cuenta.getNumeroCuenta())) {
            throw new RuntimeException("Ya existe una cuenta con ese número");
        }

        cuenta.setPersona(personaRepo.findById(cuenta.getPersona().getIdPersona())
                .orElseThrow(() -> new RuntimeException("Persona no encontrada")));

        cuenta.setTipoSaldoCuenta(tipoRepo.findById(cuenta.getTipoSaldoCuenta().getIdTipoSaldoCuenta())
                .orElseThrow(() -> new RuntimeException("Tipo de saldo no encontrado")));

        cuenta.setStatusCuenta(statusRepo.findById(cuenta.getStatusCuenta().getIdStatusCuenta())
                .orElseThrow(() -> new RuntimeException("Status de cuenta no encontrado")));

        cuenta.setFechaApertura(LocalDateTime.now());
        cuenta.setFechaCreacion(LocalDateTime.now());
        cuenta.setUsuarioCreacion(usuario);
        return repo.save(cuenta);
    }

    // Actualizar cuenta
    public CuentaCorriente actualizar(Integer id, CuentaCorriente cuentaNueva, String usuario) {
        CuentaCorriente cuenta = obtenerPorId(id);

        cuenta.setSaldoAnterior(cuentaNueva.getSaldoAnterior());
        cuenta.setDebitos(cuentaNueva.getDebitos());
        cuenta.setCreditos(cuentaNueva.getCreditos());
        cuenta.setStatusCuenta(cuentaNueva.getStatusCuenta());
        cuenta.setTipoSaldoCuenta(cuentaNueva.getTipoSaldoCuenta());
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