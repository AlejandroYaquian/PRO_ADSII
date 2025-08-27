package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.Modulo;
import com.adsii.pro_adsii.Repository.ModuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModuloService {

    @Autowired
    private ModuloRepository moduloRepository;

    public List<Modulo> listarTodos() {
        return moduloRepository.findAll();
    }

    public Optional<Modulo> obtenerPorId(Long id) {
        return moduloRepository.findById(id);
    }

    public Modulo guardar(Modulo modulo) {
        return moduloRepository.save(modulo);
    }

    public void eliminar(Long id) {
        moduloRepository.deleteById(id);
    }
}
