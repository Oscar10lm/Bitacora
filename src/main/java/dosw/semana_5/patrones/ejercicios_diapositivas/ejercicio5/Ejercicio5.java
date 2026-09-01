package dosw.semana_5.patrones.extra.ejercicio5;

public class Ejercicio5 {
    public static void main(String[] args) {
        System.out.println("--- Junior usa la Fachada ---");
        
        // Para el junior, guardar un dato es súper sencillo:
        JuniorDatabaseFacade fachada = new JuniorDatabaseFacade();
        
        fachada.guardarDato("USUARIOS", "{nombre: 'Oscar'}");
        fachada.guardarDato("FACTURAS", "{total: 50000}");
        fachada.guardarDato("USUARIOS", "{nombre: 'Camila'}");
        
        // Nota como el mensaje de "Abriendo conexión..." (del Singleton)
        // Solo sale una vez, a pesar de hacer 3 inserciones.
    }
}
