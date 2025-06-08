package repositorios;

import modelos.GamaProducto;
import util.ConexionBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GamaRepo {

    private Connection obtenerConexion() throws SQLException {
        return ConexionBD.creaConexion();
    }

    private Statement obtenerSentencia() throws SQLException {
        return obtenerConexion().createStatement();
    }

    //CRUD Crear Read/Read listas/ Actualizar Borrar

    public void crearGama(GamaProducto gamaProducto)
    {
        String qry = "INSERT INTO gama_producto VALUES (?,?,?,?)";

        try(Connection conn = obtenerConexion();
            PreparedStatement preparedStatement = conn.prepareStatement(qry))
        {
            preparedStatement.setString(1,gamaProducto.getGama());
            preparedStatement.setString(2, gamaProducto.getDescripcion_texto());
            preparedStatement.setString(3, gamaProducto.getDescripcion_html());
            preparedStatement.setString(4, gamaProducto.getImagen());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }


    public List<GamaProducto> listarGamas()
    {
        List<GamaProducto> gamas = new ArrayList<>();

        try(Statement statement = obtenerSentencia();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM gama_producto")){
            while (resultSet.next()) {
                GamaProducto gama = new GamaProducto();
                gama.setGama(resultSet.getString("gama"));
                gama.setDescripcion_texto(resultSet.getString("descripcion_texto"));
                gama.setDescripcion_html(resultSet.getString("descripcion_html"));
                gama.setImagen(resultSet.getString("imagen"));
                gamas.add(gama);
            }

        }catch (SQLException ex) {
            ex.printStackTrace();
        }

        return gamas;
    }


    public List<GamaProducto> listarUrlImagenesGama() {
        List<GamaProducto> gamas = new ArrayList<>();

        try (Statement statement = obtenerSentencia();
             ResultSet resultSet = statement.executeQuery("SELECT imagen FROM gama_producto")) {
            while (resultSet.next()) {
                GamaProducto gama = new GamaProducto();
                gama.setImagen(resultSet.getString("imagen"));
                gamas.add(gama);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return gamas;
    }


    public Optional<GamaProducto> leerGama(String id){
        GamaProducto gamaNueva = null;
        final String query = "SELECT * FROM gama_producto WHERE gama = ?";

        try (Connection c = obtenerConexion();
             PreparedStatement statement = c.prepareStatement(query)
        ){
            statement.setString(1,id);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                gamaNueva.setGama(rs.getString("gama"));
                gamaNueva.setDescripcion_texto(rs.getString("descripcion_texto"));
                gamaNueva.setDescripcion_html(rs.getString("descripcion_html"));
                gamaNueva.setImagen(rs.getString("imagen"));
            }
            rs.close();

        }catch (SQLException ex) {
            ex.printStackTrace();
        }
        return Optional.ofNullable(gamaNueva);
    }


    public void  actualizarGama(GamaProducto gamaProducto){
        String query = "UPDATE gama_producto SET gama = ?, descripcion_texto = ?, descripcion_html = ?, imagen = ? WHERE gama = ?";

        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(query)
        ) {
            statement.setString(1,gamaProducto.getGama());
            statement.setString(2,gamaProducto.getDescripcion_texto());
            statement.setString(3,gamaProducto.getDescripcion_html());
            statement.setString(4,gamaProducto.getImagen());
            statement.setString(5, gamaProducto.getGama());

            statement.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void eliminarGama(String id) {
        String query = "DELETE FROM gama_producto WHERE gama = ?";
        try (Connection connection =  obtenerConexion();
             PreparedStatement preparedStatement =  connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}