package com.example.DrogueriaLosTresHermanos.servicio;

import com.example.DrogueriaLosTresHermanos.servicio.CatalogoProductos.Categoria;
import com.example.DrogueriaLosTresHermanos.servicio.CatalogoProductos.Producto;

import java.util.List;

public interface AlmacenCatalogo {

    record Contenido(List<Categoria> categorias, List<Producto> productos) {}

    Contenido cargar();

    int guardarProducto(Producto producto, boolean nuevo);

    void cambiarFoto(int id, String foto);

    void cambiarVisibilidad(int id, boolean activo);

    void guardarCategoria(Categoria categoria, boolean nueva);

    void eliminarCategoria(String id);

    default void sincronizar(Contenido todo) {
    }

    default boolean recargarTrasCambios() {
        return false;
    }

    String origen();
}
