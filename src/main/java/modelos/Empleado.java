package modelos;

public class Empleado {
        private int codigoEmpleado;
        private String nombre;
        private String apellido1;
        private String apellido2;
        private String extension;
        private String email;
        private String codigoOficina;
        private Integer codigoJefe;
        private String puesto;

    public Empleado() {
        //Constructor vacío
    }

    public Empleado(int codigoEmpleado, String nombre, String apellido1, String apellido2, String extension, String email, String codigoOficina, Integer codigoJefe, String puesto) {
        this.codigoEmpleado = codigoEmpleado;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.extension = extension;
        this.email = email;
        this.codigoOficina = codigoOficina;
        this.codigoJefe = codigoJefe;
        this.puesto = puesto;
    }

    public int getCodigoEmpleado() {
        return codigoEmpleado;
    }

    public void setCodigoEmpleado(int codigoEmpleado) {
        this.codigoEmpleado = codigoEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCodigoOficina() {
        return codigoOficina;
    }

    public void setCodigoOficina(String codigoOficina) {
        this.codigoOficina = codigoOficina;
    }

    public Integer getCodigoJefe() {
        return codigoJefe;
    }

    public void setCodigoJefe(Integer codigoJefe) {
        this.codigoJefe = codigoJefe;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    @Override
        public String toString() {
            return String.format(
                    "Empleado: Código=%d, Nombre=%s, Apellido1=%s, Apellido2=%s, Extensión=%s, Email=%s, Oficina=%s, Código Jefe=%s, Puesto=%s",
                    codigoEmpleado,
                    nombre,
                    apellido1,
                    apellido2,
                    extension,
                    email,
                    codigoOficina,
                    codigoJefe,
                    puesto
            );
        }
    }

