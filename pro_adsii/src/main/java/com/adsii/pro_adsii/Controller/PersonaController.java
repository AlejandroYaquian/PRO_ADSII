package com.adsii.pro_adsii.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Entity.EstadoCivil;
import com.adsii.pro_adsii.Entity.Genero;
import com.adsii.pro_adsii.Entity.Persona;
import com.adsii.pro_adsii.Service.EstadoCivilService;
import com.adsii.pro_adsii.Service.GeneroService;
import com.adsii.pro_adsii.Service.PersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @GetMapping
    public List<Persona> listarPersonas() {
        return personaService.obtenerTodas();
    }

    @GetMapping("/{id}")
    public Persona obtenerPersona(@PathVariable int id) {
        return personaService.obtenerPorId(id);
    }

    @PostMapping
    public Persona crearPersona(@RequestBody Persona persona, @RequestHeader("usuario") String usuario) {
        persona.setIdPersona(0);
        return personaService.guardarPersona(persona, usuario);
    }

    @PutMapping("/{id}")
    public Persona actualizarPersona(@PathVariable int id, @RequestBody Persona persona, @RequestHeader("usuario") String usuario) {
        persona.setIdPersona(id);
        return personaService.guardarPersona(persona, usuario);
    }

    @DeleteMapping("/{id}")
    public void eliminarPersona(@PathVariable int id) {
        personaService.eliminarPersona(id);
    }

    @Autowired
    private GeneroService generoService;

    @GetMapping("/listarGeneros")
    public List<Genero> listarGeneros() {
        return generoService.listarTodos();

    }

    @Autowired
    private EstadoCivilService estadoCivilService;

    @GetMapping("/listarEstadoCivil")
    public List<EstadoCivil> listarEstadoCivil() {
        return estadoCivilService.listar();

    }

}