package com.adsii.pro_adsii.Controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Entity.BitacoraAcceso;
import com.adsii.pro_adsii.Repository.BitacoraAccesoRepository;

@RestController
@RequestMapping("/bitacora")
public class BitacoraAccesoController {

    private final BitacoraAccesoRepository bitacoraRepository;

    public BitacoraAccesoController(BitacoraAccesoRepository bitacoraRepository) {
        this.bitacoraRepository = bitacoraRepository;
    }

    @GetMapping("/listar")
    public List<BitacoraAcceso> listar() {
        return bitacoraRepository.findAll();
    }
}

