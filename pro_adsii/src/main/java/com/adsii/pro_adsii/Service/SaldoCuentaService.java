package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.SaldoCuenta;
import com.adsii.pro_adsii.Repository.SaldoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SaldoCuentaService {

    @Autowired
    private SaldoCuentaRepository repository;

    public List<SaldoCuenta> listarTodos() {
        return repository.findAll();
    }

    public SaldoCuenta obtenerPorId(Integer id) {
        Optional<SaldoCuenta> saldo = repository.findById(id);
        return saldo.orElse(null);
    }

    public SaldoCuenta guardar(SaldoCuenta saldoCuenta, String usuarioActual) {
        if (saldoCuenta.getIdSaldoCuenta() == null) {
            saldoCuenta.setFechaCreacion(LocalDateTime.now());
            saldoCuenta.setUsuarioCreacion(usuarioActual);
        } else {
            SaldoCuenta existente = repository.findById(saldoCuenta.getIdSaldoCuenta())
                .orElseThrow(() -> new RuntimeException("Saldo no encontrado"));
            saldoCuenta.setFechaCreacion(existente.getFechaCreacion());
            saldoCuenta.setUsuarioCreacion(existente.getUsuarioCreacion());
            saldoCuenta.setFechaModificacion(LocalDateTime.now());
            saldoCuenta.setUsuarioModificacion(usuarioActual);
        }
        return repository.save(saldoCuenta);
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }

    public List<SaldoCuenta> buscarPorIdCuenta(int idSaldoCuenta) {
        return repository.findByIdSaldoCuenta(idSaldoCuenta);
    }

    public List<SaldoCuenta> buscarPorIdCliente(int idPersona) {
        return repository.findByPersona_IdPersona(idPersona);
    }

    public List<SaldoCuenta> buscarPorNombreApellido(String nombre, String apellido) {
        return repository.findByPersona_NombreContainingIgnoreCaseAndPersona_ApellidoContainingIgnoreCase(nombre, apellido);
    }


    public SaldoCuenta buscarCuentaPorId(Integer id) {
        Optional<SaldoCuenta> saldo = repository.findById(id);
        return saldo.orElse(null);
    }

}
