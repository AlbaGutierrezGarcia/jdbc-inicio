package servicios;

import modelos.Oficina;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface IOficinaSrvc {

        List<Oficina> listarOficinas() throws SQLException;
        Optional<Oficina> listarOficina(String idOficina) throws SQLException;
        void crearOficina(Oficina oficina) throws IllegalAccessException, SQLException;
        void actualizarOficina(Oficina oficina) throws SQLException;
        void  eliminarOficina(String codigo) throws SQLException;
    }


