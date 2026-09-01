package dosw.semana_5.patrones.extra.ejercicio3;

public class WhatsAppAdapter extends NotificadorDecorator {
    private WhatsAppUltraAPI whatsAppAPI;
    private String telefonoDestino;

    public WhatsAppAdapter(Notificador notificador, WhatsAppUltraAPI whatsAppAPI, String telefonoDestino) {
        super(notificador);
        this.whatsAppAPI = whatsAppAPI;
        this.telefonoDestino = telefonoDestino;
    }

    @Override
    public void enviar(String mensaje) {
        super.enviar(mensaje);
        // Adaptamos la llamada: Notificador usa enviar(msg), pero la API externa usa sendSecureMessage(phone, text)
        whatsAppAPI.sendSecureMessage(telefonoDestino, mensaje);
    }
}
