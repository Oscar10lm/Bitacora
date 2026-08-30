package dosw.semana_4.taller.ejercicio2;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Sistema de Notificaciones Multicanal ---");

        Order order = new Order("ORD-2026-991");

        // Suscribimos los canales que el usuario tiene activos
        order.addObserver(new EmailNotifier());
        order.addObserver(new SmsNotifier());
        order.addObserver(new PushNotifier());

        System.out.println("\nCambiando estado a: pendiente");
        order.setStatus("pendiente");

        System.out.println("\nCambiando estado a: enviado");
        order.setStatus("enviado");
        
        System.out.println("\nCambiando estado a: entregado");
        order.setStatus("entregado");
    }
}
