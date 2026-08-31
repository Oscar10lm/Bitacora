package dosw.semana_5.preparcial_1.ejercicio5;

public class GestorConexion {
    private static GestorConexion instancia;

    private GestorConexion() {
        System.out.println("🔌 [Conexión] Abriendo conexión a la base de datos...");
    }

    public static GestorConexion getInstancia() {
        if (instancia == null) {
            instancia = new GestorConexion();
        }
        return instancia;
    }

    public void ejecutarQuery(String query) {
        System.out.println("📝 [DB] Ejecutando: " + query);
    }
}
