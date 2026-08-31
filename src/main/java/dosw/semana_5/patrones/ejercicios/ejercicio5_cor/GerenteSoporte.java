package dosw.semana_5.patrones.ejercicios.ejercicio5_cor;

public class GerenteSoporte extends SoporteHandler {
    @Override
    public void manejarTicket(int gravedad, String descripcion) {
        if (gravedad >= 5) {
            System.out.println("👔 [GERENTE] Intervención crítica. He resuelto el ticket: '" + descripcion + "'. (Gravedad " + gravedad + ")");
        } else if (siguiente != null) {
            siguiente.manejarTicket(gravedad, descripcion);
        }
    }
}
