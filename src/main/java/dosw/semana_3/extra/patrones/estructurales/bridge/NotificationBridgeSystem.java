package dosw.semana_3.extra.patrones.estructurales.bridge;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO: NOTIFICACIONES (Bridge)
 * ============================================================================
 *
 * Una aplicación envía notificaciones de distinto tipo de contenido 
 * (Mensaje de Texto, Alerta de Sistema) a través de distintos canales de 
 * envío (Email, SMS). 
 * 
 * Con herencia pura, la combinación crece con cada nuevo tipo de contenido o 
 * cada nuevo canal (MensajeTextoEmail, AlertaSistemaSMS, etc.).
 * 
 * Se aplica Bridge separando la jerarquía de Notificación (abstracción: qué 
 * se envía) de la jerarquía de Canal de Envío (implementación: cómo se envía), 
 * de modo que cualquier tipo de notificación pueda enviarse por cualquier canal.
 */
public class NotificationBridgeSystem {

    // ==========================================
    // 1. IMPLEMENTACIÓN (La Plataforma / Los Canales)
    // ==========================================
    // Define cómo se envían físicamente los mensajes
    public interface NotificationChannel {
        void send(String message);
    }

    // ==========================================
    // 2. IMPLEMENTACIONES CONCRETAS
    // ==========================================
    
    public static class EmailChannel implements NotificationChannel {
        @Override
        public void send(String message) {
            System.out.println("Enviando vía EMAIL: " + message);
        }
    }

    public static class SmsChannel implements NotificationChannel {
        @Override
        public void send(String message) {
            System.out.println("Enviando vía SMS (Texto Móvil): " + message);
        }
    }

    // ==========================================
    // 3. ABSTRACCIÓN (Capa de Control / Qué se envía)
    // ==========================================
    // Mantiene una referencia al canal y delega el envío final a él.
    public static abstract class Notification {
        
        // EL PUENTE (Composición en lugar de herencia múltiple)
        protected final NotificationChannel channel;

        public Notification(NotificationChannel channel) {
            this.channel = channel;
        }

        // Método abstracto que define el formato/contenido de lo que se enviará
        public abstract void dispatch(String message);
    }

    // ==========================================
    // 4. ABSTRACCIONES REFINADAS
    // ==========================================
    
    // Tipo: Mensaje de Texto Simple (No altera el mensaje)
    public static class TextMessageNotification extends Notification {

        public TextMessageNotification(NotificationChannel channel) {
            super(channel);
        }

        @Override
        public void dispatch(String message) {
            System.out.println("Procesando [Mensaje de Texto]...");
            // Envía el mensaje tal cual a la plataforma conectada por el puente
            channel.send(message);
        }
    }

    // Tipo: Alerta del Sistema (Formatea el mensaje para darle urgencia)
    public static class SystemAlertNotification extends Notification {

        public SystemAlertNotification(NotificationChannel channel) {
            super(channel);
        }

        @Override
        public void dispatch(String message) {
            System.out.println("Procesando [Alerta del Sistema]...");
            // Antepone la urgencia antes de cruzar el puente hacia la plataforma
            String urgentMessage = "[URGENTE] " + message;
            channel.send(urgentMessage);
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // El cliente combina el 'Qué' (Mensaje/Alerta) con el 'Cómo' (Email/SMS) 
        // dinámicamente, sin crear clases fijas como 'AlertaSistemaSMS'.

        System.out.println("--- ESCENARIO 1: Comunicación Normal ---");
        NotificationChannel sms = new SmsChannel();
        Notification normalMessage = new TextMessageNotification(sms);
        normalMessage.dispatch("Hola, tu pedido ya está en camino.");

        System.out.println("\n--- ESCENARIO 2: Fallo Crítico en Producción ---");
        NotificationChannel email = new EmailChannel();
        Notification criticalAlert = new SystemAlertNotification(email);
        criticalAlert.dispatch("El servidor de base de datos se ha caído.");
        
        System.out.println("\n--- ESCENARIO 3: Alerta enviada por SMS ---");
        // Demostrando la flexibilidad del Bridge intercambiando el canal
        Notification criticalSmsAlert = new SystemAlertNotification(sms);
        criticalSmsAlert.dispatch("Intento de acceso no autorizado detectado.");
    }
}
