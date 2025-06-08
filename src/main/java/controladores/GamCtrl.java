package controladores;

import modelos.GamaProducto;
import servicios.GamaProductoSrvc;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class GamCtrl {

        //Crear menu de usuario que te de a elegir entre distintas opciones de gama
        //Puedes crear una gama
        //Puedes ver todas las gamas
        //Puedes ver 1 gama
        //Puedes actualizar una gama dada
        //El cliente con sus huevos gordos quiere ver una gama por su imagen.
        //Puedes borrar una gama por id


        static Scanner sc = new Scanner(System.in);
        static GamaProductoSrvc gamaService = new GamaProductoSrvc();


        public static void main(String[] args) throws SQLException {
            System.out.println("---MENÚ---");
            System.out.println("1. Crear una gama");
            System.out.println("2. Ver todas las gamas");
            System.out.println("3. Ver una gama por id");
            System.out.println("4. Actualizar una gama ");
            System.out.println("5. Ver una gama por imagen");
            System.out.println("6. Borrar una gama por id");

            System.out.println("Inserta un numero para elegir la opcion");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    crearGama();
                    imprimirListaDeGamas();
                case 2:
                    imprimirListaDeGamas();
                case 3:
                    imprimirGamaporId();
                case 4:
                    actualizarGama();
                    imprimirListaDeGamas();
                case 5:
                    imprimirListaGamaPorImagen();
                    break;
                case 6:
                    eliminarGama("gamaDomingo");
                    break;
                default:
                    break;
            }
        }

        public static void crearGama() throws SQLException {
            GamaProducto gamaProducto = new GamaProducto();
            //Usuario decide que nombre le ponemos a la gama
            //System.out.println("Inserta el nombre del gama");
            //String nombreGama = sc.next();
            gamaProducto.setGama("gamaDomingo");
            gamaProducto.setDescripcion_texto("Test");
            gamaProducto.setDescripcion_html("Test");
            gamaProducto.setImagen("Test");

            gamaService.crearGama(gamaProducto);
        }

        public static void imprimirListaDeGamas() throws SQLException {
            for(GamaProducto gamaProducto : listarGamas()){
                System.out.println(gamaProducto);
            }
        }

        public static void imprimirListaGamaPorImagen() throws SQLException {
            for(GamaProducto g : listarGamasPorImagen()){
                System.out.println(g);
            }
        }

        public static void imprimirGamaporId() throws SQLException {
            System.out.println(leerGamasporId());
        }

        public static List<GamaProducto> listarGamas() throws SQLException {
            List<GamaProducto> gamas = new ArrayList<>();
            gamas = gamaService.listarGamas();
            return gamas;
        }

        public static Optional<GamaProducto> leerGamasporId() throws SQLException {
            Optional<GamaProducto> gamaProducto;
            gamaProducto = gamaService.leerGama("Herbaceas");
            return gamaProducto;
        }


        public static List<GamaProducto> listarGamasPorImagen() throws SQLException {
            List<GamaProducto> gamas = new ArrayList<>();
            gamas = gamaService.listarGamasByURL();
            return gamas;
        }


        public static void actualizarGama() throws SQLException
        {
            Optional<GamaProducto> optionalGama = leerGamasporId();

            GamaProducto gamaRecogida = optionalGama.get();
            gamaRecogida.setGama("Herramientas");
            gamaRecogida.setDescripcion_texto(gamaRecogida.getDescripcion_texto());
            gamaRecogida.setDescripcion_html("Test");
            gamaRecogida.setImagen("TestAAAAAAAAAAAAA");

            GamaProducto gamaActualizada = gamaRecogida;
            gamaService.actualizarGama(gamaActualizada);
        }

        public static void eliminarGama (String id) throws SQLException {
            Optional<GamaProducto> optionalGama = leerGamasporId();
            optionalGama = gamaService.leerGama(id);

            GamaProducto gamaElegida= optionalGama.get();

            gamaService.eliminarGama(gamaElegida.getGama());
        }


    }

