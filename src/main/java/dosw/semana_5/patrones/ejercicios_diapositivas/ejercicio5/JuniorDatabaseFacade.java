package dosw.semana_5.patrones.extra.ejercicio5;

public class JuniorDatabaseFacade {
    public void guardarDato(String tipo, String json) {
        // 1. Obtiene la conexión (Singleton)
        GestorConexion conexion = GestorConexion.getInstancia();
        
        // 2. Fabrica el repositorio adecuado (Factory Method)
        Repositorio repo = FabricaRepositorios.crear(tipo);
        
        // 3. Guarda el dato
        repo.guardar(json, conexion);
    }
}
