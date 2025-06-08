package com.Micromercado.app;

import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class WebSecurityConfig {

    private final UsuarioRepository usuarioRepository;

    public WebSecurityConfig(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            Usuario user = usuarioRepository.findByCorreo(username);
            if (user == null) throw new UsernameNotFoundException("Usuario no encontrado");

            return User.builder()
                    .username(user.getCorreo())
                    .password(user.getContraseña())
                    .roles(user.getRol().name())
                    .build();
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                // Acceso público
                .requestMatchers("/login", "/css/**", "/h2-console/**").permitAll()

                // Rutas visibles para ADMINISTRADOR y CLIENTE
                .requestMatchers("/productos/ver").hasAnyRole("ADMINISTRADOR", "CLIENTE")

                // Funciones de inventario
                .requestMatchers("/productos/inventario", "/productos/actualizar-stock").hasAnyRole("ADMINISTRADOR", "INVENTARIO")

                // Crear y guardar productos solo para admin
                .requestMatchers("/productos/nuevo", "/productos/guardar").hasRole("ADMINISTRADOR")

                // Lista general de productos solo para admin e inventario
                .requestMatchers("/productos").hasAnyRole("ADMINISTRADOR", "INVENTARIO")

                // Ventas
                .requestMatchers("/ventas/**").hasAnyRole("VENDEDOR", "ADMINISTRADOR")

                // Auditoría
                .requestMatchers("/auditoria/**").hasAnyRole("AUDITOR", "ADMINISTRADOR")

                // Cualquier otra ruta requiere autenticación
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutSuccessUrl("/login?logout")
                .permitAll()
            );

        // Para permitir H2 Console
        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}

