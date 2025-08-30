package com.adsii.pro_adsii.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.transaction.annotation.Transactional;

import com.adsii.pro_adsii.Repository.UsuarioRepository;

@RestController
@RequestMapping("/usuarios")
public class UsuarioAdminController {
    private final UsuarioRepository usuarioRepo;
    public UsuarioAdminController(UsuarioRepository usuarioRepo){ this.usuarioRepo = usuarioRepo; }

    @PostMapping("/{id}/estatus/{idStatus}")
    @Transactional
    public String cambiarEstatus(@PathVariable String id, @PathVariable Integer idStatus){
        usuarioRepo.cambiarEstatus(id, idStatus);
        return "OK";
    }
}
