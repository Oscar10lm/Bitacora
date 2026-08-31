package dosw.semana_5.patrones.ejercicios.ejercicio5_cor;

public class IngenieroSoporte extends SoporteHandler {
    @Override
    public void manejarTicket(int gravedad, String descripcion) {
        if (gravedad == 3 || gravedad == 4) {
            System.out.println("🛠️ [INGENIERO] He resuelto el ticket complejo: '" + descripcion + "'. (Gravedad " + gravedad + ")");
        } else if (siguiente != null) {
            System.out.println("🛠️ [INGENIERO] Escalando ticket crítico...");
            siguiente.manejarTicket(gravedad, descripcion);
        }
    }
}
