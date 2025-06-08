import repositorios.OficinaRepo;


import java.util.Scanner;

public class MainJardineria {

private static Scanner sc = new Scanner(System.in);
private static OficinaRepo oficinaRepo = new OficinaRepo();

public static void main(String[] args) {
    System.out.println("¡Bienvenido a la aplicación de gestión de Oficinas de Jardinería!");
    String opcion;  
// Aquí guardaremos la opción que elija el usuario


private static Scanner sc = new Scanner(System.in);

    // ¿Por qué 'private'?
    // Porque queremos que este objeto Scanner solo sea accesible dentro de esta clase (MainJardineria).
    // Es una buena práctica de encapsulamiento: ocultar los detalles internos de la implementación.

    // ¿Por qué 'static'?
    // Porque solo necesitamos una única instancia del Scanner para toda la aplicación.
    // Si no fuera static, cada vez que creáramos una instancia de MainJardineria (lo cual no haríamos
    // en una aplicación de consola como esta, pero si en otras arquitecturas), se crearía un nuevo Scanner,
    // lo cual es ineficiente y podría causar problemas si intentas cerrarlo varias veces.
    // Al ser static, el Scanner se inicializa una vez cuando la clase se carga y es compartido por todos los métodos.
   
private static OficinaRepo oficinaRepo = new OficinaRepo();

    // ¿Por qué 'private'?
    // Por la misma razón que el Scanner. El acceso a la lógica de la base de datos (a través del repositorio)
    // debe controlarse desde esta clase principal. No queremos que cualquier otra parte del programa
    // pueda manipular directamente el repositorio si no es a través de MainJardineria.

    // ¿Por qué 'static'?
    // Generalmente, en aplicaciones de consola simples como esta, solo necesitas una instancia
    // de tu repositorio para gestionar las operaciones de la base de datos. Hacerlo 'static'
    // asegura que solo se cree una vez y se reutilice. En aplicaciones más complejas (con frameworks como Spring),
    // se usarían otros patrones (como la inyección de dependencias) para gestionar esto,
    // pero para nuestro caso, 'static' es práctico y suficiente.
    