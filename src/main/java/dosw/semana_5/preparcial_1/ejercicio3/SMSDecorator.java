package dosw.semana_5.preparcial_1.ejercicio3;

public class SMSDecorator extends NotificadorDecorator {
    public SMSDecorator(Notificador notificador) {
        super(notificador);
    }

    @Override
    public void enviar(String mensaje) {
        super.enviar(mensaje);
        System.out.println("📱 [SMS] Enviando: " + mensaje);
    }
}
