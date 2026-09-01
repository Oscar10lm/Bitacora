package dosw.semana_5.patrones.extra.ejercicio5;

public class UsuarioRepository implements Repositorio {
    @Override
    public void guardar(String json, GestorConexion conexion) {
        conexion.ejecutarQuery("INSERT INTO usuarios (datos) VALUES ('" + json + "')");
    }
}
