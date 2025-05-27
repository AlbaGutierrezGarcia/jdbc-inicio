Apuntes JDBC: Conexiones y Consultas en Java

1. CONEXIÓN A LA BASE DE DATOS

Para conectar Java con una base de datos PostgreSQL, utilizamos JDBC (Java Database Connectivity).

1.1 Parámetros básicos de conexión:
    - URL: Dirección del servidor y base de datos, ejemplo:
            jdbc:postgresql://localhost:5432/jardineria1

    - Usuario: Nombre del usuario con permisos en la base de datos,
             ejemplo: jardinero

    - Contraseña: Password asociado al usuario,
                ejemplo: jardinero

2. GESTIÓN DE RECURSOS
Cuando trabajamos con JDBC, manejamos objetos que consumen recursos del sistema y la base de datos:

    - Connection: Conexión con la base de datos.
    - Statement: Consulta o comando SQL que vamos a ejecutar.
    - ResultSet: Resultado obtenido de la consulta.

Es fundamental cerrar estos recursos después de usarlos para evitar fugas de memoria y problemas de rendimiento.

3. OPCIONES PARA CERRAR RECURSOS CON EJEMPLO

import java.sql.*;

public class ComparacionConexion {

    static final String url = "jdbc:postgresql://localhost:5432/jardineria1";
    static final String user = "jardinero";
    static final String password = "jardinero";

    public static void main(String[] args) {
        System.out.println("Opción 1: Cierre manual de recursos");
        opcion1();

        System.out.println("\nOpción 2: try-with-resources (cierre automático)");
        opcion2();
    }


    // OPCIÓN 1: CIERRE MANUAL

    static void opcion1() {
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT codigo_cliente, nombre_cliente FROM cliente";

        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo_cliente");
                String nombre = resultSet.getString("nombre_cliente");
                System.out.println("Código: " + codigo + " | Nombre: " + nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Cerrar en orden inverso, verificando nulos
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    // OPCIÓN 2: Try-with-resources (RECOMENDADO)

    static void opcion2() {
        String query = "SELECT codigo_cliente, nombre_cliente FROM cliente";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                int codigo = resultSet.getInt("codigo_cliente");
                String nombre = resultSet.getString("nombre_cliente");
                System.out.println("Código: " + codigo + " | Nombre: " + nombre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

 * Explicación:
        - En opción 1 se cierran manualmente los recursos (ResultSet, Statement, Connection) en el bloque finally.
            Esto obliga a controlar cierres explícitos para evitar fugas de memoria.
        - En opción 2 se usa try-with-resources, que cierra automáticamente los recursos al finalizar el bloque try,
            simplificando el código y asegurando que no se olviden cierres.

 * Nota:
 * Defino la consulta SQL fuera del while para no repetir la ejecución de la consulta en cada iteración.

