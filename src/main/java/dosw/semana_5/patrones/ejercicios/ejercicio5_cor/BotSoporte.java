package dosw.semana_5.patrones.ejercicios.ejercicio5_cor;

public class BotSoporte extends SoporteHandler {
    @Override
    public void manejarTicket(int gravedad, String descripcion) {
        if (gravedad == 1) {
            System.out.println("🤖 [BOT] He resuelto el ticket: '" + descripcion + "'. (Gravedad 1)");
        } else if (siguiente != null) {
            System.out.println("🤖 [BOT] Escalando ticket...");
            siguiente.manejarTicket(gravedad, descripcion);
        }
    }
}
