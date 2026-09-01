package dosw.semana_3.ejercicios_diapositivas.patrones.creacionales.factory_method;

public class NotificationSystem {

    // ==========================================
    // 1. PRODUCTO (La interfaz común)
    // ==========================================
    public interface Notification {
        void send(String message);
    }

    // ==========================================
    // 2. PRODUCTOS CONCRETOS (Implementaciones)
    // ==========================================
    public static class EmailNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("Enviando notificación por EMAIL: " + message);
        }
    }

    public static class SmsNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("Enviando notificación por SMS: " + message);
        }
    }

    public static class PushNotification implements Notification {
        @Override
        public void send(String message) {
            System.out.println("Enviando notificación por PUSH MÓVIL: " + message);
        }
    }

    // ==========================================
    // 3. CREADOR (Clase Abstracta / Superclase)
    // ==========================================
    public static abstract class NotificationSender {
        
        // El "Factory Method": delega la instanciación a las subclases
        protected abstract Notification createNotification();

        // Lógica principal: usa el producto devuelto por el Factory Method
        public void dispatchNotification(String message) {
            System.out.println("--- Preparando el entorno de envío ---");
            
            // Instanciamos usando la fábrica
            Notification notification = createNotification();
            
            // Usamos el objeto polimórfico
            notification.send(message);
            
            System.out.println("--- Envío finalizado ---\n");
        }
    }

    // ==========================================
    // 4. CREADORES CONCRETOS (Subclases)
    // ==========================================
    public static class EmailNotificationSender extends NotificationSender {
        @Override
        protected Notification createNotification() {
            return new EmailNotification();
        }
    }

    public static class SmsNotificationSender extends NotificationSender {
        @Override
        protected Notification createNotification() {
            return new SmsNotification();
        }
    }

    public static class PushNotificationSender extends NotificationSender {
        @Override
        protected Notification createNotification() {
            return new PushNotification();
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // El cliente selecciona qué creador instanciar según sus necesidades.
        // A partir de aquí, el flujo de envío (dispatchNotification) es ciego al canal.

        NotificationSender emailSender = new EmailNotificationSender();
        emailSender.dispatchNotification("¡Bienvenido a la plataforma!");

        NotificationSender smsSender = new SmsNotificationSender();
        smsSender.dispatchNotification("Tu código de verificación es 1234");

        NotificationSender pushSender = new PushNotificationSender();
        pushSender.dispatchNotification("¡Tienes 3 nuevos mensajes!");
    }
}
