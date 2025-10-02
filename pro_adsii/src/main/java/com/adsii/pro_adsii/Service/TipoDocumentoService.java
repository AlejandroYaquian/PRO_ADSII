package com.adsii.pro_adsii.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.Entity.TipoDocumento;
import com.adsii.pro_adsii.Repository.TipoDocumentoRepository;

@Service
public class TipoDocumentoService {

    private final TipoDocumentoRepository repository;

    public TipoDocumentoService(TipoDocumentoRepository repository) {
        this.repository = repository;
    }

    public List<TipoDocumento> listar() {
        return repository.findAll();
    }

    public Optional<TipoDocumento> buscarPorId(Integer id) {
        return repository.findById(id);
    }

    public TipoDocumento crear(TipoDocumento tipoDocumento, String usuario) {
        tipoDocumento.setFechaCreacion(LocalDateTime.now());
        tipoDocumento.setUsuarioCreacion(usuario);
        return repository.save(tipoDocumento);
    }

    public TipoDocumento actualizar(Integer id, TipoDocumento datos, String usuario) {
        return repository.findById(id).map(td -> {
            td.setNombre(datos.getNombre());
            td.setFechaModificacion(LocalDateTime.now());
            td.setUsuarioModificacion(usuario);
            return repository.save(td);
        }).orElseThrow(() -> new RuntimeException("No encontrado"));
    }

    public void eliminar(Integer id) {
        repository.deleteById(id);
    }
}