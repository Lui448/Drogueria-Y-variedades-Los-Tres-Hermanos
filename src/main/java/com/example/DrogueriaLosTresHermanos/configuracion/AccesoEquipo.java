package com.example.DrogueriaLosTresHermanos.configuracion;

import com.example.DrogueriaLosTresHermanos.servicio.DirectorioAccesos;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DefaultAuthenticationEventPublisher;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Component;

@Component
public class AccesoEquipo {

    private final DirectorioAccesos directorio;
    private final LimiteIntentosLogin limite;
    private final AuthenticationManager autenticador;
    private final SecurityContextHolderStrategy contextos = SecurityContextHolder.getContextHolderStrategy();
    private final SecurityContextRepository repositorio = new HttpSessionSecurityContextRepository();
    private final WebAuthenticationDetailsSource detalles = new WebAuthenticationDetailsSource();

    public AccesoEquipo(DirectorioAccesos directorio, LimiteIntentosLogin limite, PasswordEncoder codificador, ApplicationEventPublisher eventos) {
        this.directorio = directorio;
        this.limite = limite;
        DaoAuthenticationProvider proveedor = new DaoAuthenticationProvider(directorio);
        proveedor.setPasswordEncoder(codificador);
        ProviderManager gestor = new ProviderManager(proveedor);
        gestor.setAuthenticationEventPublisher(new DefaultAuthenticationEventPublisher(eventos));
        this.autenticador = gestor;
    }

    public enum Resultado { NO_ES_DEL_EQUIPO, BLOQUEADO, CLAVE_INCORRECTA, DENTRO }

    public Resultado ingresar(String correo, String clave, HttpServletRequest peticion, HttpServletResponse respuesta) {
        if (correo == null || !directorio.esCuentaDelEquipo(correo)) {
            return Resultado.NO_ES_DEL_EQUIPO;
        }
        if (limite.estaBloqueada(peticion.getRemoteAddr())) {
            return Resultado.BLOQUEADO;
        }
        UsernamePasswordAuthenticationToken intento = UsernamePasswordAuthenticationToken.unauthenticated(correo.strip(), clave == null ? "" : clave);
        intento.setDetails(detalles.buildDetails(peticion));
        Authentication autenticacion;
        try {
            autenticacion = autenticador.authenticate(intento);
        } catch (AuthenticationException e) {
            return Resultado.CLAVE_INCORRECTA;
        }
        peticion.getSession(true);
        peticion.changeSessionId();
        SecurityContext contexto = contextos.createEmptyContext();
        contexto.setAuthentication(autenticacion);
        contextos.setContext(contexto);
        repositorio.saveContext(contexto, peticion, respuesta);
        return Resultado.DENTRO;
    }
}
