package dosw.semana_5.patrones.extra.ejercicio3;

public class Ejercicio3 {
    public static void main(String[] args) {
        System.out.println("--- 1. Enviando solo Email ---");
        Notificador notificadorEmail = new NotificadorBase();
        notificadorEmail.enviar("¡Bienvenido a la plataforma!");

        System.out.println("\n--- 2. Enviando Email + SMS ---");
        Notificador notificadorSMS = new SMSDecorator(new NotificadorBase());
        notificadorSMS.enviar("Tu código de seguridad es 1234");

        System.out.println("\n--- 3. Enviando Email + SMS + WhatsApp (Adapter) ---");
        WhatsAppUltraAPI apiExterna = new WhatsAppUltraAPI();
        
        // Armamos la cadena completa usando Decorator + Adapter
        Notificador notificadorTotal = new WhatsAppAdapter(
                new SMSDecorator(
                        new NotificadorBase()
                ), 
                apiExterna, 
                "+573000000000"
        );
        
        notificadorTotal.enviar("¡Oferta especial del 50% de descuento!");
    }
}
