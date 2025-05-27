package modelos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter

@Setter

@AllArgsConstructor

@NoArgsConstructor

public class Empleado {

    private int codigoEmpleado;

    private String nombre;

    private String apellido1;

    private String apellido2;

    private String extension;

    private String email;

    private String codigoOficina;

    private Integer codigoJefe; // Puede ser null

    private String puesto;

}

