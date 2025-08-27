package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.Modulo;
import com.adsii.pro_adsii.Service.ModuloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/modulo")
public class ModuloController {

    @Autowired
    private ModuloService moduloService;

    @GetMapping("/listar")
    public String listarModulos(Model model) {
        model.addAttribute("modulos", moduloService.listarTodos());
        return "modulo/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("modulo", new Modulo());
        return "modulo/formulario";
    }

    @PostMapping("/guardar")
    public String guardarModulo(@ModelAttribute Modulo modulo) {
        moduloService.guardar(modulo);
        return "redirect:/modulo/listar";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable Long id, Model model) {
        Modulo modulo = moduloService.obtenerPorId(id).orElse(null);
        model.addAttribute("modulo", modulo);
        return "modulo/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarModulo(@PathVariable Long id) {
        moduloService.eliminar(id);
        return "redirect:/modulo/listar";
    }
}
