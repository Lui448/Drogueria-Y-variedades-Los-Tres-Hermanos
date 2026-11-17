package com.example.DrogueriaLosTresHermanos.controlador;

import com.example.DrogueriaLosTresHermanos.servicio.DirectorioAccesos;
import com.example.DrogueriaLosTresHermanos.modelo.PerfilAdmin;
import com.example.DrogueriaLosTresHermanos.modelo.Permiso;
import com.example.DrogueriaLosTresHermanos.modelo.PlanSuscripcion;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Arrays;
import java.util.List;

@Controller
public class PaginaControlador {

    private final DirectorioAccesos directorio;

    public PaginaControlador(DirectorioAccesos directorio) {
        this.directorio = directorio;
    }

    private List<PlanSuscripcion> obtenerPlanes() {
        return Arrays.asList(
            new PlanSuscripcion(1L, "Básico",
                "Acceso esencial al catálogo y pedidos por WhatsApp.",
                9900, 3, false, false, true, false),

            new PlanSuscripcion(2L, "Estándar",
                "Ahorra en tus compras del mes con un buen descuento.",
                19900, 5, false, false, true, false),

            new PlanSuscripcion(3L, "Regular",
                "Domicilio prioritario y descuento ampliado para clientes frecuentes.",
                29900, 10, true, false, true, false),

            new PlanSuscripcion(4L, "Premium",
                "Máximo ahorro, domicilio gratis y acceso exclusivo a ofertas.",
                39900, 15, true, true, true, true)
        );
    }

    @GetMapping("/")
    public String inicio(Model modelo) {
        modelo.addAttribute("pagina", "inicio");
        modelo.addAttribute("titulo", "Inicio");
        return "index";
    }

    @GetMapping("/medicamentos")
    public String medicamentos(Model modelo, Authentication autenticacion) {
        modelo.addAttribute("pagina", "medicamentos");
        modelo.addAttribute("editarImagenes", autenticacion != null && autenticacion.getAuthorities().stream()
            .anyMatch(a -> Permiso.PRODUCTOS_EDITAR.getCodigo().equals(a.getAuthority())));
        modelo.addAttribute("titulo", "Medicamentos");
        return "medicamentos";
    }

    @GetMapping("/variedades")
    public String variedades(Model modelo) {
        modelo.addAttribute("pagina", "variedades");
        modelo.addAttribute("titulo", "Variedades");
        return "variedades";
    }

    @GetMapping("/servicios")
    public String servicios(Model modelo) {
        modelo.addAttribute("pagina", "servicios");
        modelo.addAttribute("titulo", "Servicios");
        return "servicios";
    }

    @GetMapping("/suscripciones")
    public String suscripciones(Model modelo) {
        modelo.addAttribute("pagina", "suscripciones");
        modelo.addAttribute("titulo", "Suscripciones");
        modelo.addAttribute("planes", obtenerPlanes());
        return "suscripciones";
    }

    @GetMapping("/nosotros")
    public String nosotros(Model modelo) {
        modelo.addAttribute("pagina", "nosotros");
        modelo.addAttribute("titulo", "Nosotros");
        return "nosotros";
    }

    @GetMapping("/contacto")
    public String contacto(Model modelo) {
        modelo.addAttribute("pagina", "contacto");
        modelo.addAttribute("titulo", "Contacto");
        return "contacto";
    }

    @GetMapping("/registro")
    public String registro(Model modelo) {
        modelo.addAttribute("pagina", "registro");
        modelo.addAttribute("titulo", "Registro");
        return "registro";
    }

    @GetMapping("/login")
    public String login(Model modelo) {
        modelo.addAttribute("pagina", "login");
        modelo.addAttribute("titulo", "Iniciar sesión");
        return "login";
    }

    @GetMapping("/perfil")
    public String perfil(Model modelo) {
        modelo.addAttribute("pagina", "perfil");
        modelo.addAttribute("titulo", "Mi perfil");
        return "perfil";
    }

    @GetMapping("/checkout")
    public String checkout(Model modelo) {
        modelo.addAttribute("pagina", "checkout");
        modelo.addAttribute("titulo", "Finalizar pedido");
        return "checkout";
    }

    @GetMapping("/recibo")
    public String recibo(Model modelo) {
        modelo.addAttribute("pagina", "recibo");
        modelo.addAttribute("titulo", "Confirmación de pedido");
        return "recibo";
    }

    @GetMapping("/pago-suscripcion")
    public String pagoSuscripcion(Model modelo) {
        modelo.addAttribute("pagina", "pago-suscripcion");
        modelo.addAttribute("titulo", "Pagar Suscripción");
        return "pago-suscripcion";
    }

    @GetMapping("/admin")
    public String adminPanel(Model modelo, Authentication autenticacion) {
        PerfilAdmin perfil = directorio.perfil(autenticacion);
        modelo.addAttribute("pagina", "admin");
        modelo.addAttribute("titulo", "Panel de Administración");
        modelo.addAttribute("adminCorreo", perfil.correo());
        modelo.addAttribute("perfil", perfil);
        modelo.addAttribute("seccionActiva", "resumen");
        return "admin";
    }

    @GetMapping("/admin/login")
    public String adminLogin() {
        return "redirect:/login";
    }
}
