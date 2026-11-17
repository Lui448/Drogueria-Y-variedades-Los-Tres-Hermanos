package com.example.DrogueriaLosTresHermanos.servicio;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.function.Supplier;

public class BaseDatos {

    public static class NoDisponible extends RuntimeException {
        public NoDisponible() {
            super("Este servicio no está disponible en esta versión de la página.");
        }
    }

    public static class ErrorUsuario extends RuntimeException {
        public ErrorUsuario(String mensaje) {
            super(mensaje);
        }
    }

    private final JdbcTemplate jdbc;
    private final TransactionTemplate transaccion;

    public BaseDatos(DataSource origen) {
        this.jdbc = origen == null ? null : new JdbcTemplate(origen);
        this.transaccion = origen == null ? null : new TransactionTemplate(new DataSourceTransactionManager(origen));
    }

    public boolean disponible() {
        return jdbc != null;
    }

    public JdbcTemplate jdbc() {
        if (jdbc == null) {
            throw new NoDisponible();
        }
        return jdbc;
    }

    public <T> T enTransaccion(Supplier<T> trabajo) {
        if (transaccion == null) {
            throw new NoDisponible();
        }
        return transaccion.execute(estado -> trabajo.get());
    }

    public static String mensajeDeRegla(Throwable e) {
        for (Throwable t = e; t != null; t = t.getCause()) {
            if (t instanceof java.sql.SQLException s && "45000".equals(s.getSQLState())) {
                return s.getMessage();
            }
        }
        return null;
    }
}
