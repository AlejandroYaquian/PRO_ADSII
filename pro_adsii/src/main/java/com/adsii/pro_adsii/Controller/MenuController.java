package com.adsii.pro_adsii.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adsii.pro_adsii.Entity.Menu;
import com.adsii.pro_adsii.Service.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @GetMapping("/listar")
    public List<Menu> listar() {
        return menuService.listarTodos();
    }

    @GetMapping("/{id}")
    public Menu obtener(@PathVariable Integer id) {
        return menuService.obtenerPorId(id);
    }

    @PostMapping("/guardar")
    public Menu guardar(@RequestBody Menu menu) {
        String usuarioActual = "Admin"; // usuario real del login
        return menuService.guardar(menu, usuarioActual);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminar(@PathVariable Integer id) {
        menuService.eliminar(id);
    }
}



