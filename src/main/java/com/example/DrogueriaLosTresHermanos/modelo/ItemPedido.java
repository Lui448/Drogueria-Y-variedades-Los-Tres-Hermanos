package com.example.DrogueriaLosTresHermanos.modelo;

public class ItemPedido {

    private Long id;
    private Producto producto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;

    public ItemPedido() {}

    public ItemPedido(Producto producto, int cantidad, double precioUnitario) {
        this.producto       = producto;
        this.cantidad       = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal       = precioUnitario * cantidad;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Producto getProducto() { return producto; }
    public void setProducto(Producto producto) { this.producto = producto; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
        this.subtotal = this.precioUnitario * cantidad;
    }

    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
        this.subtotal       = precioUnitario * this.cantidad;
    }

    public double getSubtotal() { return subtotal; }

    @Override
    public String toString() {
        return "ItemPedido{producto=" + (producto != null ? producto.getNombre() : "null")
             + ", cantidad=" + cantidad + ", subtotal=" + subtotal + "}";
    }
}
