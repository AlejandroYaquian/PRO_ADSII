package com.adsii.pro_adsii.Controller;

import com.adsii.pro_adsii.Entity.Genero;
import com.adsii.pro_adsii.Service.GeneroService;

import java.util.List;

<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.*;


=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

>>>>>>> Stashed changes
@RestController
@RequestMapping("/genero")
public class GeneroController {

    private final GeneroService generoService;

    public GeneroController(GeneroService generoService) {
        this.generoService = generoService;
    }

    @GetMapping("/listar")
    public List<Genero> listar() {
        return generoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Genero obtener(@PathVariable Long id) {
        return generoService.obtenerPorId(id).orElse(null);
    }

    @PostMapping("/guardar")
    public Genero guardar(@RequestBody Genero genero) {
<<<<<<< Updated upstream
        return generoService.guardar(genero);
=======
        String usuarioActual = "Admin"; // usuario real del login
        return generoService.guardar(genero, usuarioActual);
>>>>>>> Stashed changes
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Long id) {
        generoService.eliminar(id);
    }
}
