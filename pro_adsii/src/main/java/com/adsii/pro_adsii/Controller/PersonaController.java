package com.adsii.pro_adsii.Controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.adsii.pro_adsii.Repository.PersonaRepository;
import com.adsii.pro_adsii.Service.EstadoCivilService;
import com.adsii.pro_adsii.Service.GeneroService;
import com.adsii.pro_adsii.Service.PersonaService;

@RestController
@RequestMapping("/persona")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private EstadoCivilService estadoCivilService;

    @Autowired
private PersonaRepository PersonaRepository;


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
        persona.setIdPersona(null);
        return personaService.guardarPersona(persona, usuario);
    }

@PutMapping("/{id}")
public ResponseEntity<?> actualizarPersona(
        @PathVariable int id,
        @RequestBody Persona nuevaPersona,
        @RequestHeader("usuario") String usuario) {

    Persona personaExistente = PersonaRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Persona no encontrada"));

    personaExistente.setNombre(nuevaPersona.getNombre());
    personaExistente.setApellido(nuevaPersona.getApellido());
    personaExistente.setFechaNacimiento(nuevaPersona.getFechaNacimiento());
    personaExistente.setIdGenero(nuevaPersona.getIdGenero());
    personaExistente.setDireccion(nuevaPersona.getDireccion());
    personaExistente.setTelefono(nuevaPersona.getTelefono());
    personaExistente.setCorreoElectronico(nuevaPersona.getCorreoElectronico());
    personaExistente.setIdEstadoCivil(nuevaPersona.getIdEstadoCivil());
    personaExistente.setUsuarioModificacion(usuario);
    personaExistente.setFechaModificacion(LocalDateTime.now());

    PersonaRepository.save(personaExistente);

    return ResponseEntity.ok("Persona actualizada correctamente");
}



    @DeleteMapping("/{id}")
    public void eliminarPersona(@PathVariable int id) {
        personaService.eliminarPersona(id);
    }

    @GetMapping("/listarGeneros")
    public List<Genero> listarGeneros() {
        return generoService.listarTodos();
    }

    @GetMapping("/listarEstadoCivil")
    public List<EstadoCivil> listarEstadoCivil() {
        return estadoCivilService.listar();
    }
}
