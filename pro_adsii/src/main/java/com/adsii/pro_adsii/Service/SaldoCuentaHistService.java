package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.SaldoCuentaHist;
import com.adsii.pro_adsii.Repository.SaldoCuentaHistRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaldoCuentaHistService {

    private final SaldoCuentaHistRepository repo;

    public SaldoCuentaHistService(SaldoCuentaHistRepository repo) {
        this.repo = repo;
    }

    public List<SaldoCuentaHist> buscar(Integer anio, Integer mes, Integer idPersona) {
        return repo.buscar(anio, mes, idPersona);
    }
}
