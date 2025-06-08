package controladores;


import modelos.Oficina;
import servicios.IOficinaSrvc;
import servicios.OficinaSrvc;

import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class OficinaCtrl {

    IOficinaSrvc oficinaSrvc = new OficinaSrvc();
    Scanner scanner = new Scanner(System.in);

    public void listarOficinas() throws SQLException {
        oficinaSrvc.listarOficinas().forEach(System.out::println);
    }

    public void leerOficinaxId() throws SQLException {
        System.out.println("Indique el id de la oficina: ");
        // preguntamos el id de la oficina y vamos a buscar sus datos
        Optional<Oficina> ofi = oficinaSrvc.listarOficina(scanner.nextLine());
        ofi.ifPresentOrElse(System.out::println, ()->{
            System.out.println("No existe el id de la oficina: ");
        });
    }

    public void crearOficina() throws IllegalAccessException, SQLException {
        Oficina ofi = new Oficina();
        //Obtenemos la lista de campos mediante reflexion
        Field[] listaCampos = ofi.getClass().getDeclaredFields();
        // Generamos una lista con el nombre del campo y su valor mediante reflexión
        for (Field field : listaCampos) {
            field.setAccessible(true);
            System.out.println("Campo:  " + field.getName() + " - valor: ");
            String valor = scanner.nextLine();
            field.set(ofi, valor);
        }

        oficinaSrvc.crearOficina(ofi);

        //Comprobamos si se ha creado, esto es completamente opcional porque nunca vamos a llegar aquí o casi nunca
        Optional<Oficina> ofiGrabada = oficinaSrvc.listarOficina(ofi.getCodigoOficina());
        ofiGrabada.ifPresentOrElse(System.out::println, ()->{
            System.out.println("No existe el id de la oficina: ");
        });
    }

    private List<String> listarCodigoOficina() throws IllegalAccessException, SQLException {
        List<String> codigos = new ArrayList<>();
        List<Oficina> oficinas = oficinaSrvc.listarOficinas();

        for (Oficina oficina : oficinas) {
            codigos.add(oficina.getCodigoOficina());
        }

        return codigos;
    }


    private void printCodigos() throws SQLException, IllegalAccessException {
        System.out.println("Codigos:" + listarCodigoOficina());
    }

    public void modificarOficina() throws SQLException, IllegalAccessException {
        System.out.println("Modificar oficina:");
        System.out.print("Introduce el código de la oficina que quieres modificar entre : ");
        printCodigos();
        String codigoModificar = scanner.nextLine();
        try {
            OficinaSrvc ofiService = new OficinaSrvc();
            Optional<Oficina> oficinaExistenteOpt = ofiService.listarOficina(codigoModificar);

            leerYModificarCampos(scanner, oficinaExistenteOpt.get(), ofiService);

            // ¿Utilizamos las posibilidades de la clase Optional para programación funcional
//                        oficinaExistenteOpt.ifPresentOrElse(
//                                ofi -> {
//                                    try {
//                                        System.out.println(ofiService.actualizarOficina(preguntarCampos(ofi))  //utilizamos un if ternario
//                                                ? "Oficina actualizada correctamente."
//                                                : "No se pudo actualizar la oficina :"
//                                        );
//                                    } catch (SQLException e) {
//                                        throw new RuntimeException(e);
//                                    }
//                                },
//                                () -> System.out.println("No se encontró ninguna oficina con ese código.")
//                        );
        } catch (SQLException | IllegalAccessException e) {
            System.out.println("Error al modificar la oficina: " + e.getMessage());
        }
    }

    private void leerYModificarCampos(Scanner scanner, Oficina oficina, OficinaSrvc oficinaSrvc) throws SQLException, IllegalAccessException {
        // Obtenemos los campos de la oficina
        Field[] listaCampos = oficina.getClass().getDeclaredFields();
        // Generamos una lista con el nombre del campo y su valor mediante reflexión
        for(int i = 0; i<listaCampos.length; i++) {
            Field field = listaCampos[i];
            field.setAccessible(true);
            System.out.println("Campo " + (i+1) + ": " + field.getName() + " - valor: " + field.get(oficina));
        }
        while (true) {
            // Preguntamos el campo que se desea modificar
            System.out.println("Indique el numero del campo que desea modificar (si desea salir, indique cualquier otro número):");
            int campo = scanner.nextInt();
            // Si el campo es mayor que la última opción, salimos del método
            if(campo > listaCampos.length) {
                break;
            }
            // Preguntamos el nuevo valor
            scanner.nextLine();
            System.out.println("Que valor le desea dar al campo?");
            String valor = scanner.nextLine();
            switch (campo) {
                case 1:
                    System.out.println("El código de la oficina no se puede modificar");
                    break;
                case 2:
                    oficina.setCiudad(valor.isEmpty() ? oficina.getCiudad() : valor);
                    break;
                case 3:
                    oficina.setPais(valor.isEmpty() ? oficina.getPais() : valor);
                    break;
                case 4:
                    oficina.setRegion(valor.isEmpty() ? oficina.getRegion() : valor);
                    break;
                case 5:
                    oficina.setCodigoPostal(valor.isEmpty() ? oficina.getCodigoPostal() : valor);
                    break;
                case 6:
                    oficina.setTelefono(valor.isEmpty() ? oficina.getTelefono() : valor);
                    break;
                case 7:
                    oficina.setLineaDireccion1(valor.isEmpty() ? oficina.getLineaDireccion1() : valor);
                    break;
                case 8:
                    oficina.setLineaDireccion2(valor.isEmpty() ? oficina.getLineaDireccion2() : valor);
                default:
                    break;
            }
        }
        oficinaSrvc.actualizarOficina(oficina);
    }


    public void eliminarOficina() throws SQLException, IllegalAccessException {
        System.out.println("Eliminar oficina:");
        System.out.print("Código de la oficina a eliminar: ");
        String codEliminar = scanner.nextLine();
        // Comprobamos si existe antes de eliminar
        Optional<Oficina> oficinaEliminar = oficinaSrvc.listarOficina(codEliminar);
        if (oficinaEliminar.isEmpty()) {
            System.out.println("Oficina no encontrada. No se puede eliminar.");
            return;
        }
        // Si existe, la eliminamos
        oficinaSrvc.eliminarOficina(codEliminar);
        System.out.println("Oficina eliminada correctamente.");
    }

    private Oficina preguntarCampos(Oficina oficinaExistente) {
        System.out.println("Datos actuales:");
        System.out.println(oficinaExistente);

        System.out.print("Nueva ciudad: ");
        String ciudadNueva = scanner.nextLine();
        oficinaExistente.setCiudad(ciudadNueva.isEmpty() ? oficinaExistente.getCiudad() : ciudadNueva);

        System.out.print("Nuevo país: ");
        String paisNuevo = scanner.nextLine();
        oficinaExistente.setPais(paisNuevo.isEmpty() ? oficinaExistente.getPais() : paisNuevo);

        System.out.print("Nueva región: ");
        String regionNueva = scanner.nextLine();
        oficinaExistente.setRegion(regionNueva.isEmpty() ? oficinaExistente.getRegion() : regionNueva);

        System.out.print("Nuevo código postal: ");
        String postalNuevo = scanner.nextLine();
        oficinaExistente.setCodigoPostal(postalNuevo.isEmpty() ? oficinaExistente.getCodigoPostal() : postalNuevo);

        System.out.print("Nuevo teléfono: ");
        String telefonoNuevo = scanner.nextLine();
        oficinaExistente.setTelefono(telefonoNuevo.isEmpty() ? oficinaExistente.getTelefono() : telefonoNuevo);

        System.out.print("Nueva dirección línea 1: ");
        String direccion1Nueva = scanner.nextLine();
        oficinaExistente.setLineaDireccion1(direccion1Nueva.isEmpty() ? oficinaExistente.getLineaDireccion1() : direccion1Nueva);

        System.out.print("Nueva dirección línea 2: ");
        String direccion2Nueva = scanner.nextLine();
        oficinaExistente.setLineaDireccion2(direccion2Nueva.isEmpty() ? oficinaExistente.getLineaDireccion2() : direccion2Nueva);
        return oficinaExistente;
    }
}