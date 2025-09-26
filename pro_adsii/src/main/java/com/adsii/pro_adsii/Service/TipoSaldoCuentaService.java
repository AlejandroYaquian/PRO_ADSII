package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.TipoSaldoCuenta;
import com.adsii.pro_adsii.Repository.TipoSaldoCuentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class TipoSaldoCuentaService {

    @Autowired
    private TipoSaldoCuentaRepository tipoRepo;

    public List<TipoSaldoCuenta> listarTodos() {
        return tipoRepo.findAll();
    }

    public TipoSaldoCuenta obtenerPorId(Long id) {
        Optional<TipoSaldoCuenta> tipo = tipoRepo.findById(id);
        return tipo.orElse(null);
    }

    public TipoSaldoCuenta guardar(TipoSaldoCuenta tipo, String usuarioActual) {
        if (tipo.getIdTipoSaldoCuenta() == null) {
            tipo.setFechaCreacion(LocalDateTime.now());
            tipo.setUsuarioCreacion(usuarioActual);
        } else {
            TipoSaldoCuenta existente = tipoRepo.findById(tipo.getIdTipoSaldoCuenta())
                    .orElseThrow(() -> new RuntimeException("TipoSaldoCuenta no encontrado"));
            tipo.setFechaCreacion(existente.getFechaCreacion());
            tipo.setUsuarioCreacion(existente.getUsuarioCreacion());
            tipo.setFechaModificacion(LocalDateTime.now());
            tipo.setUsuarioModificacion(usuarioActual);
        }
        return tipoRepo.save(tipo);
    }

    public void eliminar(Long id) {
        tipoRepo.deleteById(id);
    }
}
