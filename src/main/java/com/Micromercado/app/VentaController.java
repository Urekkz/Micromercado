package com.Micromercado.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;

@Controller
@RequestMapping("/ventas")
public class VentaController {

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping
    public String listar(Model model, @RequestParam(value = "success", required = false) String success) {
        model.addAttribute("ventas", ventaRepository.findAll());
        model.addAttribute("success", success); // mensaje opcional
        return "ventas";
    }

    @GetMapping("/nueva")
    public String nuevaForm(Model model) {
        model.addAttribute("venta", new Venta());
        model.addAttribute("productos", productoRepository.findAll());
        return "nueva_venta";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Venta venta, Principal principal, RedirectAttributes redirectAttributes) {
        Usuario vendedor = usuarioRepository.findByCorreo(principal.getName());

        Long productoId = venta.getProducto().getId();
        Producto producto = productoRepository.findById(productoId).orElseThrow();

        venta.setProducto(producto);
        venta.setVendedor(vendedor);
        venta.setTotal(venta.getCantidad() * producto.getPrecio());

        ventaRepository.save(venta);

        // Agrega el mensaje para mostrar en la vista
        redirectAttributes.addAttribute("success", "Venta registrada correctamente");

        return "redirect:/ventas";
    }
}
