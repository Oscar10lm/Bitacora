package dosw.semana_5.patrones.extra.ejercicio3;

public class NotificadorBase implements Notificador {
    @Override
    public void enviar(String mensaje) {
        System.out.println("📧 [EMAIL] Enviando: " + mensaje);
    }
}
