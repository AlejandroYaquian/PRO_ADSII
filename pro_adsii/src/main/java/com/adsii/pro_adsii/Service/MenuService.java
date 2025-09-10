package com.adsii.pro_adsii.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.adsii.pro_adsii.Entity.Menu;
import com.adsii.pro_adsii.Repository.MenuRepository;

@Service
public class MenuService {

	@Autowired
	private MenuRepository menuRepository;

	public List<Menu> listarTodos() {
		return menuRepository.findAll();
	}

	public Menu obtenerMenuPorId(Integer id) {

		Optional<Menu> menu = menuRepository.findById(id);	
		return menu.isPresent() ? menu.get() : null;
	}

	public Optional<Menu> obtenerPorId(Integer id) {
        return menuRepository.findById(id);
    }

	public Menu guardar(Menu menu, String usuarioActual) {
		if (menu.getIdModulo() == null) {
            menu.setFechaCreacion(LocalDateTime.now());
            menu.setUsuarioCreacion(usuarioActual);
        } else {
            Menu existente = menuRepository.findById(menu.getIdModulo())
                    .orElseThrow(() -> new RuntimeException("Menu no encontrado"));
            menu.setFechaCreacion(existente.getFechaCreacion());
            menu.setUsuarioCreacion(existente.getUsuarioCreacion());
            menu.setFechaModificacion(LocalDateTime.now());
            menu.setUsuarioModificacion(usuarioActual);
        }
		return menuRepository.save(menu);
	}	

	public void eliminar(Integer id) {
        menuRepository.deleteById(id);
    }

	    public List<Menu> listarPorModulo(Integer idModulo) {
        return menuRepository.findByIdModulo(idModulo);
    }
}

