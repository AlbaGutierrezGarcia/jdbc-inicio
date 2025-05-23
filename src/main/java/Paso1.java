import java.sql.*;

public class Paso1 {
    public static void main(String[] args) {
        //Definimos los parámetros de conexión a la BBDD
        String url = "jdbc:postgresql://localhost:5432/jardineria1";
        String user = "jardinero";
        String password = "jardinero";
        //"127.0.0.1"//localhost:3306/postgres";//


//       Así si lo cerramos manualmente  try {
//            //Creamos la instancia de la conexión a la BDD
//            Connection conex = DriverManager.getConnection(url,user,password);
//            //Creamos una instancia de sentencia para poder enviar consultas al servidor de BBDD
//            Statement stament = conex.createStatement();
//            //Definimos la consulta, la enviamos al servidor (ejecutamos la sentencia) y obtenemos el resultado devuelto por el servidor
//            ResultSet rs = stament.executeQuery("select * from cliente");
//            //En el ResulSet tenemos todos los registros devueltos por el servidor dentro de un iterador
//            while (rs.next()) {
//                System.out.println(rs.getInt(1)+" "+rs.getString(2));
//            }
//            //Cerramos en orden invertido
//            rs.close();
//            stament.close();
//            conex.close();
//
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}

        // Así si se cierran solos
        try (
                Connection connection1 = DriverManager.getConnection(url, user, password);
                Statement statement = connection1.createStatement();
                ResultSet resultSet = statement.executeQuery(
                        "SELECT c.nombre_cliente, e.nombre, e.apellido1 " +
                                "FROM cliente c " +
                                "JOIN empleado e ON c.codigo_empleado_rep_ventas = e.codigo_empleado");
        )
        {
            //en el resulset tenemos todos los registros devueltos por el servidor dentro de un iterador
            while (resultSet.next()) {
                String nombreCliente = resultSet.getString("nombre_cliente");
                String nombreRep = resultSet.getString("nombre");
                String apellidoRep = resultSet.getString("apellido1");

                System.out.println("Cliente: " + nombreCliente);
                System.out.println("Representante: " + nombreRep + " " + apellidoRep);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}