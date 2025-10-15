package com.adsii.pro_adsii.Service;

import com.adsii.pro_adsii.Entity.Sucursal;
import com.adsii.pro_adsii.Repository.SucursalRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SucursalService {

    @Autowired
    private SucursalRepository sucursalRepository;

    public List<Sucursal> obtenerTodas() {
        return sucursalRepository.findAll();
    }

    @SuppressWarnings("null")
    public Sucursal guardarSucursal(Sucursal sucursal) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String usuario = request.getHeader("usuario");

        if (sucursal.getIdSucursal() == 0) {
            sucursal.setFechaCreacion(LocalDateTime.now());
            sucursal.setUsuarioCreacion(usuario);
        } else {
            Sucursal existente = sucursalRepository.findById(sucursal.getIdSucursal()).orElse(null);
            if (existente != null) {
                sucursal.setFechaCreacion(existente.getFechaCreacion());
                sucursal.setUsuarioCreacion(existente.getUsuarioCreacion());
            }
            sucursal.setFechaModificacion(LocalDateTime.now());
            sucursal.setUsuarioModificacion(usuario);
        }

        return sucursalRepository.save(sucursal);
    }

    public Sucursal obtenerPorId(int id) {
        return sucursalRepository.findById(id).orElse(null);
    }

    public void eliminarSucursal(int id) {
        sucursalRepository.deleteById(id);
    }
}
