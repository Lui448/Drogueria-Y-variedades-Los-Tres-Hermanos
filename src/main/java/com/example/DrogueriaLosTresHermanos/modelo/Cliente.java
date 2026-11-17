package com.example.DrogueriaLosTresHermanos.modelo;

public class Cliente {

    private Long id;
    private String nombre;
    private String correo;
    private String telefono;
    private String direccion;
    private String rol;
    private PlanSuscripcion plan;

    public Cliente() {}

    public Cliente(Long id, String nombre, String correo, String telefono, String rol) {
        this.id       = id;
        this.nombre   = nombre;
        this.correo   = correo;
        this.telefono = telefono;
        this.rol      = rol;
    }


    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCorreo() { return correo; }
    public void setCorreo(String correo) { this.correo = correo; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }

    public PlanSuscripcion getPlan() { return plan; }
    public void setPlan(PlanSuscripcion plan) { this.plan = plan; }

    public boolean tieneSuscripcionActiva() {
        return plan != null && plan.getDescuentoPorcentaje() > 0;
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nombre='" + nombre + "', correo='" + correo + "'}";
    }
}
