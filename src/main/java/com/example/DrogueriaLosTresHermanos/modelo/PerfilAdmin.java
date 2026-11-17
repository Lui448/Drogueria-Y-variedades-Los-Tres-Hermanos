package com.example.DrogueriaLosTresHermanos.modelo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public record PerfilAdmin(String correo, String rolId, String rolNombre, Set<String> permisos, List<ModuloGestion> modulos) {

    public boolean puede(String permiso) {
        return permisos.contains(permiso);
    }

    public boolean operaClientes() {
        return puede(Permiso.CLIENTES_VER.getCodigo());
    }

    public boolean operaPagos() {
        return puede(Permiso.SUSCRIPCIONES_VER.getCodigo());
    }

    public boolean veProductos() {
        return puede(Permiso.PRODUCTOS_VER.getCodigo());
    }

    public boolean vePedidos() {
        return puede(Permiso.VENTAS_VER.getCodigo());
    }

    public boolean gestionaAccesos() {
        return puede(Permiso.ROLES_GESTIONAR.getCodigo()) || puede(Permiso.USUARIOS_GESTIONAR.getCodigo());
    }

    public String permisosComoTexto() {
        return permisos.stream().sorted().collect(Collectors.joining(" "));
    }
}
