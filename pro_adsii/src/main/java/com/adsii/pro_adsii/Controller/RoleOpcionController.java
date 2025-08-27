package com.adsii.pro_adsii.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Repository.RoleOpcionRepository;

@RestController
@RequestMapping(path="/RoleOpcion")
public class RoleOpcionController {

    @Autowired
    RoleOpcionRepository roleOpcionRepository;

    /* 
    @GetMapping("/opcionesRol/{idRole}")
    public ResponseEntity<List<Opcion>> obtenerOpcionesPorRol(@PathVariable Integer idRole) {
        List<Opcion> opciones = roleOpcionRepository.findOpcionesByIdRole(idRole);
        return opciones.isEmpty()
            ? ResponseEntity.noContent().build()
            : ResponseEntity.ok(opciones);
    }*/



}
