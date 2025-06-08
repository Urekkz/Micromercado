package com.Micromercado.app;

import java.time.LocalDateTime;

public class Auditor {
    private String accion;
    private String usuario;
    private LocalDateTime fechaHora;

    public Auditor(String accion, String usuario, LocalDateTime fechaHora) {
        this.accion = accion;
        this.usuario = usuario;
        this.fechaHora = fechaHora;
    }

    public String getAccion() {
        return accion;
    }

    public String getUsuario() {
        return usuario;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }
}
