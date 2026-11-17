package com.example.DrogueriaLosTresHermanos.servicio;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ArchivosClientes {

    private final FotosProductos fotos;
    private final FotosProductos comprobantes;

    public ArchivosClientes(@Value("${app.fotos-clientes:datos/fotos-clientes}") String fotos,
                            @Value("${app.comprobantes:datos/comprobantes}") String comprobantes) {
        this.fotos = new FotosProductos(fotos);
        this.comprobantes = new FotosProductos(comprobantes);
    }

    public FotosProductos fotos() {
        return fotos;
    }

    public FotosProductos comprobantes() {
        return comprobantes;
    }
}
