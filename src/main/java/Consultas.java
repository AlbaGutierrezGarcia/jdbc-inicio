import java.sql.*;

public class Consultas {
//         Datos de conexión.
//         Reutilizables en cualquier metodo sin repetir código.
//        Static: pertenecen a la clase, no a una instancia.
//        Si mañana cambias la URL, solo la cambias una vez.

    static final String url = "jdbc:postgresql://localhost:5432/jardineria1";
    static final String user = "jardinero";
    static final String password = "jardinero";
    //"127.0.0.1"//localhost:3306/jardineria1";//

    public static void main(String[] args) {
        try (
                Connection connection = DriverManager.getConnection(url, user, password)) {
            // Ejecutamos las consultas
            consulta11(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void consulta1(Connection connection) throws SQLException {
        System.out.println("---- CONSULTA 1: Todos los clientes ----");
        String query = "SELECT * FROM cliente";
        try (
                Statement stmt = connection.createStatement();
                ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int codigo = rs.getInt("codigo_cliente");
                String nombre = rs.getString("nombre_cliente");
                System.out.println("Código: " + codigo + " | Nombre: " + nombre);
            }
        }
    }

    static void consulta2(Connection connection) throws SQLException {
        System.out.println("---- CONSULTA 2: Cliente con su representante ----");
        String query = "SELECT c.nombre_cliente, e.nombre, e.apellido1 " +
                "FROM cliente c " +
                "JOIN empleado e ON c.codigo_empleado_rep_ventas = e.codigo_empleado";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nombreCliente = rs.getString("nombre_cliente");
                String nombreRep = rs.getString("nombre");
                String apellidoRep = rs.getString("apellido1");

                System.out.println("Cliente: " + nombreCliente +
                        " | Representante: " + nombreRep + " " + apellidoRep);
            }
        }
    }

    static void consulta7(Connection connection) throws SQLException {
        System.out.println("---- CONSULTA 7: Cliente + representante + ciudad de la oficina del representante ----");
        String query = "SELECT DISTINCT c.nombre_cliente, e.nombre AS nombre_representante, o.ciudad " +
                "FROM cliente c " +
                "JOIN empleado e ON c.codigo_empleado_rep_ventas = e.codigo_empleado " +
                "JOIN oficina o ON e.codigo_oficina = o.codigo_oficina " +
                "ORDER BY e.nombre ASC, o.ciudad ASC";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                String nombreCliente = rs.getString("nombre_cliente");
                String nombreRep = rs.getString("nombre_representante");
                String ciudadOficina = rs.getString("ciudad");

                System.out.println("Cliente: " + nombreCliente +
                        " | Representante: " + nombreRep +
                        " | Ciudad oficina: " + ciudadOficina);
            }
        }
    }

    static void consulta8(Connection connection) throws SQLException {
        System.out.println("---- CONSULTA 8: Devuelve un listado con el nombre de los empleados junto con el nombre de sus jefes. ----");
        String query = "SELECT e.nombre AS empleado, j.nombre AS jefe " +
                "FROM empleado e " +
                "JOIN empleado j ON e.codigo_jefe = j.codigo_empleado " +
                "ORDER BY e.nombre ASC";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nombreEmpleado = rs.getString("empleado");  // Usar alias empleado
                String nombreJefe = rs.getString("jefe");          // Usar alias jefe

                System.out.println("Empleado: " + nombreEmpleado + " | Jefe: " + nombreJefe);
            }
        }
    }

    static void consulta9(Connection connection) throws SQLException {
        System.out.println("---- CONSULTA 9: Devuelve un listado que muestre el nombre de cada empleado, el nombre de su jefe y el nombre del jefe. ----");
        String query = " SELECT e.nombre AS empleado, j.nombre AS jefe, jj.nombre AS jefazo " +
                "FROM empleado e " +
                "JOIN empleado j on e.codigo_jefe= j.codigo_empleado " +
                "JOIN empleado jj on j.codigo_jefe = jj.codigo_empleado";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nombreEmpleado = rs.getString("empleado");
                String nombreJefe = rs.getString("jefe");
                String nombreJefazo = rs.getString("jefazo");

                System.out.println("Empleado: " + nombreEmpleado +
                        " | Jefe: " + nombreJefe +
                        " | Jefazo: " + nombreJefazo);
            }
        }
    }

    static void consulta10(Connection connection) throws SQLException {
        System.out.println("---- CONSULTA 10: Devuelve el nombre de los clientes a los que no se les ha entregado a tiempo un pedido. ----");
        String query = "SELECT c.nombre_cliente, p.codigo_pedido, p.fecha_esperada " +
                "FROM pedido p " +
                "JOIN cliente c on p.codigo_cliente = c.codigo_cliente " +
                "WHERE p.estado= 'Entregado' AND p.fecha_entrega > fecha_esperada "
                + "ORDER BY c.nombre_cliente ASC" ;
            try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {
                boolean hayResultados = false;

                while (rs.next()) {
                    hayResultados = true;
                    String nombreCliente = rs.getString("nombre_cliente");
                    int codigoPedido = rs.getInt("codigo_pedido");
                    Date fechaEsperada = rs.getDate("fecha_esperada");

                    System.out.println("Cliente: " + nombreCliente +
                            " | Pedido: " + codigoPedido +
                            " | Esperado: " + fechaEsperada);
                }

                if (!hayResultados) {
                    System.out.println("No hay clientes con pedidos entregados tarde.");
                }
            }
    }
    static void consulta11(Connection connection) throws SQLException {
        System.out.println("---- CONSULTA 11: Devuelve un listado de las diferentes gamas de producto que ha comprado cada cliente. ----");
        String query = "SELECT c.nombre_cliente,c.codigo_cliente, p.codigo_pedido, prod.codigo_producto, prod.gama " +
                "FROM cliente c " +
                "JOIN pedido p on c.codigo_cliente= p.codigo_cliente " +
                "JOIN detalle_pedido dp on dp.codigo_pedido=p.codigo_pedido " +
                "JOIN producto prod on prod.codigo_producto = dp.codigo_producto " +
                "ORDER BY c.codigo_cliente ASC" ;
        try (Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                String nombreCliente = rs.getString("nombre_cliente");
                int codigoCliente = rs.getInt("codigo_cliente");
                int codigoPedido = rs.getInt("codigo_pedido");
                String codigoProducto = rs.getString("codigo_producto");
                String gama = rs.getString("gama");

                System.out.println("Cliente: " + nombreCliente +
                        " | Código Cliente: " + codigoCliente +
                        " | Código Pedido: " + codigoPedido +
                        " | Código Producto: " + codigoProducto +
                        " | Gama: " + gama );
            }
        }
    }
        }



