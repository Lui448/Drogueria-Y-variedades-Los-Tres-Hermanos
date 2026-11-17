package com.example.DrogueriaLosTresHermanos.modelo;

import java.time.LocalDateTime;
import java.util.List;

public class Pedido {

    private Long id;
    private String numeroPedido;
    private Cliente cliente;
    private List<ItemPedido> items;
    private double subtotal;
    private double descuentoAplicado;
    private double total;
    private String modalidad;
    private String direccionEntrega;
    private String estado;
    private LocalDateTime fechaCreacion;

    public Pedido() {
        this.fechaCreacion = LocalDateTime.now();
        this.estado        = "pendiente";
    }

    public Pedido(String numeroPedido, Cliente cliente, List<ItemPedido> items,
                  double subtotal, double descuentoAplicado, double total,
                  String modalidad, String direccionEntrega) {
        this.numeroPedido     = numeroPedido;
        this.cliente          = cliente;
        this.items            = items;
        this.subtotal         = subtotal;
        this.descuentoAplicado = descuentoAplicado;
        this.total            = total;
        this.modalidad        = modalidad;
        this.direccionEntrega = direccionEntrega;
        this.fechaCreacion    = LocalDateTime.now();
        this.estado           = "pendiente";
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNumeroPedido() { return numeroPedido; }
    public void setNumeroPedido(String numeroPedido) { this.numeroPedido = numeroPedido; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public List<ItemPedido> getItems() { return items; }
    public void setItems(List<ItemPedido> items) { this.items = items; }

    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }

    public double getDescuentoAplicado() { return descuentoAplicado; }
    public void setDescuentoAplicado(double descuentoAplicado) {
        this.descuentoAplicado = descuentoAplicado;
    }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getModalidad() { return modalidad; }
    public void setModalidad(String modalidad) { this.modalidad = modalidad; }

    public String getDireccionEntrega() { return direccionEntrega; }
    public void setDireccionEntrega(String direccionEntrega) {
        this.direccionEntrega = direccionEntrega;
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Override
    public String toString() {
        return "Pedido{numero='" + numeroPedido + "', total=" + total
             + ", estado='" + estado + "'}";
    }
}
