package com.Micromercado.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class DashboardController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @GetMapping("/dashboard")
    public String redirigirPorRol(Principal principal, Model model) {
        Usuario usuario = usuarioRepository.findByCorreo(principal.getName());

        switch (usuario.getRol()) {
            case ADMINISTRADOR:
                return "dashboard";

            case VENDEDOR:
                return "dashboard_vendedor";

            case INVENTARIO:
                model.addAttribute("productos", productoRepository.findAll());
                return "dashboard_inventario";

            case AUDITOR:
                return "dashboard_auditor";

            case CLIENTE:
                return "dashboard_cliente";

            default:
                return "redirect:/login";
        }
    }
}
