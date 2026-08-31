package dosw.semana_5.patrones.ejercicios.ejercicio5_cor;

public class AsesorSoporte extends SoporteHandler {
    @Override
    public void manejarTicket(int gravedad, String descripcion) {
        if (gravedad == 2) {
            System.out.println("🧑‍💻 [ASESOR] He resuelto el ticket: '" + descripcion + "'. (Gravedad 2)");
        } else if (siguiente != null) {
            System.out.println("🧑‍💻 [ASESOR] Escalando ticket...");
            siguiente.manejarTicket(gravedad, descripcion);
        }
    }
}
