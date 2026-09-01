package dosw.semana_5.patrones.ejercicios.ejercicio13_combo7;

public class UsuarioModerno {
    private int id;
    private String nombre;
    private String rol;

    public UsuarioModerno(int id, String nombre, String rol) {
        this.id = id;
        this.nombre = nombre;
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "UsuarioModerno { ID=" + id + ", Nombre='" + nombre + "', Rol='" + rol + "' }";
    }
}
