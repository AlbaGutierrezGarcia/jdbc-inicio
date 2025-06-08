package util;


import modelos.Empleado;

import java.util.List;

public class ReadLists {

    public void Read(List<Empleado> lista)
    {
        for (Empleado empleado : lista){
            System.out.println(empleado);
        }
    }

}