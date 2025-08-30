package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.Sucursal;
import com.adsii.pro_adsii.Repository.SucursalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> obtenerTodas() {
        return sucursalRepository.findAll();
    }

    public Sucursal guardarSucursal(Sucursal sucursal) {
        return sucursalRepository.save(sucursal);
    }

    public Sucursal obtenerPorId(int id) {
        return sucursalRepository.findById(id).orElse(null);
    }

    public void eliminarSucursal(int id) {
        sucursalRepository.deleteById(id);
    }
}