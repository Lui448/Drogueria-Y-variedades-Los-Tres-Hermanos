package com.example.DrogueriaLosTresHermanos.configuracion;

import com.example.DrogueriaLosTresHermanos.modelo.RolSistema;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties("app")
public record EquipoProperties(List<Miembro> equipo) {

    public EquipoProperties {
        equipo = equipo == null ? List.of() : List.copyOf(equipo);
    }

    public record Miembro(String correo, RolSistema rol, String clave) {}
}
