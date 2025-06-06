package modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

//Representación de la información de la tabla oficina jardineria, estas clases representa el model de MVC
//Etiquetas: Getters y setters para ahorrar los getters, los otros 2 inserta el constructor vacio y el cons con parametros
//CADA ENTIDAD TIENE SU PROPIO REPOSITORIO

public class Oficina {
    //Todos estos valores pertenecen a la tabla oficina de nuestra bd Jardineria
    private String codigoOficina;
    private String ciudad;
    private String pais;
    private String region;
    private String codigoPostal;
    private String telefono;
    private String lineaDireccion1;
    private String lineaDireccion2;


    public String toString(){
            return String.format( "Oficina: código=%s, ciudad=%s, pais=%s, region=%s, codigo postal=%s, telefono=%s, direccion1=%s, direccion2=%s",
                    codigoOficina, ciudad , pais, region, codigoPostal, telefono, lineaDireccion1, lineaDireccion2);

        }
    }
