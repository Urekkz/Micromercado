package com.Micromercado.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoRepository productoRepository;

    // Listar productos (para todos)
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "productos"; // Vista: productos.html
    }

    // Mostrar formulario para crear producto
    @GetMapping("/nuevo")
    public String nuevoForm(Model model) {
        model.addAttribute("producto", new Producto());
        return "nuevo_producto"; // Vista: nuevo_producto.html
    }

    // Guardar producto desde el formulario
    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Producto producto) {
        productoRepository.save(producto);
        return "redirect:/productos";
    }

    // Mostrar inventario (para INVENTARIO o ADMIN)
    @GetMapping("/inventario")
    public String verInventario(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "inventario"; // Vista: inventario.html
    }

    // Actualizar stock de productos
    @PostMapping("/actualizar-stock")
    public String actualizarStock(@RequestParam("id") Long id, @RequestParam("stock") int stock) {
        Producto producto = productoRepository.findById(id).orElseThrow();
        producto.setStock(stock);
        productoRepository.save(producto);
        return "redirect:/productos/inventario";
    }
    
 // Dashboard del rol INVENTARIO
    @GetMapping("/dashboard-inventario")
    public String mostrarDashboardInventario(Model model) {
        model.addAttribute("productos", productoRepository.findAll());
        return "dashboard_inventario"; // Vista: dashboard_inventario.html
    }

}
