package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.PeriodoCierreMes;
import com.adsii.pro_adsii.Repository.PeriodoCierreMesRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PeriodoCierreMesService {

    
    private final PeriodoCierreMesRepository repo;

    public PeriodoCierreMesService(PeriodoCierreMesRepository repo) {
        this.repo = repo;
    }

    public List<PeriodoCierreMes> listarAbiertos() { return repo.findAbiertos(); }
    public List<PeriodoCierreMes> listarTodos()     { return repo.findAll(); }

    @Transactional
    public PeriodoCierreMes cerrar(int anio, int mes, String usuario) {
        PeriodoCierreMes p = repo.findPeriodoAbierto(anio, mes)
            .orElseThrow(() -> new IllegalArgumentException("No existe período abierto " + anio + "-" + mes));
        var ahora = LocalDateTime.now();
        p.setFechaCierre(ahora);
        p.setFechaModificacion(ahora);
        p.setUsuarioModificacion( (usuario==null || usuario.isBlank()) ? "system" : usuario );
        return repo.save(p);
    }
}

