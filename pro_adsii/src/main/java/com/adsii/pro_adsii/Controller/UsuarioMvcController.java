package com.adsii.pro_adsii.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import com.adsii.pro_adsii.Repository.UsuarioRepository;


    @Controller
public class UsuarioMvcController {
    private final UsuarioRepository usuarioRepo;

    public UsuarioMvcController(UsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepo.findAll());
        return "usuarios"; // busca templates/usuarios.html
    }
}
