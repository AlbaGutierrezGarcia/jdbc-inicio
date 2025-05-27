package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

//Clase reutilizable para abrir y cerrar conexiones.

//Maneja una única conexión compartida (singleton).

//Centraliza credenciales y lógica de conexión.

//Ventaja: Si mañana cambias de base de datos o URL, lo haces en un solo lugar.

public class ConexionBD {

    private static final String url = "jdbc:postgresql://localhost:5432/jardineria1";
    private static final String usuario = "jardinero";
    private static final String contraseña = "jardinero";

    // el metodo debe devolver una variable de tipo Connection que será utilizada por el código que llama
    private static Connection conex = null;

    public static Connection obtenerConexion() throws SQLException {
        if (conex == null || conex.isClosed()) {
            conex = DriverManager.getConnection(url, usuario, contraseña);
        }
        return conex;
    }

    private Statement obtenerSentencia() throws SQLException {
        return obtenerConexion().createStatement();
    }


    public static void cerrarConexion() throws SQLException {
        if (conex != null && !conex.isClosed()) {
            conex.close();
        }
    }
}