package com.Micromercado.app;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/auditoria")
public class AuditoriaController {

    // Lista simulada de informes
    private static final List<Auditor> informes = new ArrayList<>();

    // Inicializamos algunos informes de ejemplo
    static {
        informes.add(new Auditor("Ingreso al sistema", "admin@micromercado.com", LocalDateTime.now().minusDays(1)));
        informes.add(new Auditor("Actualización de stock", "inventario@micromercado.com", LocalDateTime.now().minusHours(3)));
        informes.add(new Auditor("Venta registrada", "vendedor1@micromercado.com", LocalDateTime.now().minusMinutes(30)));
    }

    @GetMapping
    public String verInformes(Model model) {
        model.addAttribute("informes", informes);
        return "dashboard_auditoria";
    }
}
