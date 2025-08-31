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

import com.adsii.pro_adsii.Entity.Menu;
import com.adsii.pro_adsii.Repository.MenuRepository;


@RestController
@RequestMapping("/menu")
@Service

public class MenuService {
	
	@Autowired
	MenuRepository ur;
	
	@GetMapping(path ="/buscar")
	
	public List<Menu> buscar(){
		return ur.findAll();
	}
	
	@PostMapping(path="/guardar")
	public Menu guardar(@RequestBody Menu menu) {
		return ur.save(menu);
	}
	
	@DeleteMapping(path="/eliminar/{idmenu}")
	public void eliminar(@PathVariable int idmenu) {
		ur.deleteById(idmenu);
	}

}
