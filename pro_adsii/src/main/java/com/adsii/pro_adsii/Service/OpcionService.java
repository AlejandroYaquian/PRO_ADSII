package com.adsii.pro_adsii.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Entity.Opcion;
import com.adsii.pro_adsii.Repository.OpcionRepository;

@RestController
@RequestMapping("/opcion")
@Service

public class OpcionService {

	@Autowired
	OpcionRepository ur;
	
	@GetMapping(path="/buscar")
	
	public List<Opcion> buscar(){
		return ur.findAll();
	}
	
	@PostMapping(path="/guardar")
	public Opcion guardar(@RequestBody Opcion opcion) {
		return ur.save(opcion);
	}
	
	@DeleteMapping(path="/eliminar/{idopcion}")
	public void eliminar(@PathVariable int idopcion) {
		ur.deleteById(idopcion);
	}
}
