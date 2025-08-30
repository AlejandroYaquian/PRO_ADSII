package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.Genero;
import com.adsii.pro_adsii.Service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/genero")
public class GeneroController {

    @Autowired
    private GeneroService generoService;

    @GetMapping("/listar")
public String listarGeneros(Model model) {
    model.addAttribute("generos", generoService.listarTodos());
    model.addAttribute("genero", new Genero()); // Para el formulario
    return "genero";
}


    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("genero", new Genero());
        return "genero/formulario";
    }

    @PostMapping("/guardar")
    public String guardarGenero(@ModelAttribute Genero genero) {
        generoService.guardar(genero);
        return "redirect:/genero/listar";
    }

    @GetMapping("/editar/{id}")
public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
    model.addAttribute("generos", generoService.listarTodos());
    model.addAttribute("genero", generoService.obtenerPorId(id).orElse(new Genero()));
    return "genero";
}


    @GetMapping("/eliminar/{id}")
    public String eliminarGenero(@PathVariable Long id) {
        generoService.eliminar(id);
        return "redirect:/genero/listar";
    }
}
