package com.Micromercado.app;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Venta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Producto producto;

    @ManyToOne
    private Usuario vendedor;

    private int cantidad;
    private double total;

    private LocalDateTime fecha = LocalDateTime.now();

    // Getters
    public Long getId() {
        return id;
    }

    public Producto getProducto() {
        return producto;
    }

    public Usuario getVendedor() {
        return vendedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getTotal() {
        return total;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public void setVendedor(Usuario vendedor) {
        this.vendedor = vendedor;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }
}
