package com.Micromercado.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class MicromercadoApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicromercadoApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (usuarioRepository.findByCorreo("admin@micromercado.com") == null) {
                Usuario admin = new Usuario();
                admin.setNombre("Administrador");
                admin.setCorreo("admin@micromercado.com");
                admin.setContraseña(passwordEncoder.encode("1234"));
                admin.setRol(Rol.ADMINISTRADOR);
                usuarioRepository.save(admin);
                System.out.println("✅ Usuario admin creado: admin@micromercado.com / 1234");
            }

            if (usuarioRepository.findByCorreo("vendedor@micromercado.com") == null) {
                Usuario vendedor = new Usuario();
                vendedor.setNombre("Vendedor");
                vendedor.setCorreo("vendedor@micromercado.com");
                vendedor.setContraseña(passwordEncoder.encode("1234"));
                vendedor.setRol(Rol.VENDEDOR);
                usuarioRepository.save(vendedor);
                System.out.println("✅ Usuario vendedor creado: vendedor@micromercado.com / 1234");
            }

            if (usuarioRepository.findByCorreo("inventario@micromercado.com") == null) {
                Usuario inventario = new Usuario();
                inventario.setNombre("Encargado Inventario");
                inventario.setCorreo("inventario@micromercado.com");
                inventario.setContraseña(passwordEncoder.encode("1234"));
                inventario.setRol(Rol.INVENTARIO);
                usuarioRepository.save(inventario);
                System.out.println("✅ Usuario inventario creado: inventario@micromercado.com / 1234");
            }

            if (usuarioRepository.findByCorreo("auditor@micromercado.com") == null) {
                Usuario auditor = new Usuario();
                auditor.setNombre("Auditor");
                auditor.setCorreo("auditor@micromercado.com");
                auditor.setContraseña(passwordEncoder.encode("1234"));
                auditor.setRol(Rol.AUDITOR);
                usuarioRepository.save(auditor);
                System.out.println("✅ Usuario auditor creado: auditor@micromercado.com / 1234");
            }

            if (usuarioRepository.findByCorreo("cliente@micromercado.com") == null) {
                Usuario cliente = new Usuario();
                cliente.setNombre("Cliente");
                cliente.setCorreo("cliente@micromercado.com");
                cliente.setContraseña(passwordEncoder.encode("1234"));
                cliente.setRol(Rol.CLIENTE);
                usuarioRepository.save(cliente);
                System.out.println("✅ Usuario cliente creado: cliente@micromercado.com / 1234");
            }
        };
    }
}
