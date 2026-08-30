package dosw.semana_3.taller.solid;

/**
 * -------------------------------------------------------------------
 * #3 El Sistema de Notificaciones
 * -------------------------------------------------------------------
 *
 * (2) PROBLEMA PRINCIPAL:
 * La clase NotificationService utiliza condicionales (if-else) para
 * determinar el comportamiento según el tipo de notificación. Para agregar
 * un nuevo canal (ej. Telegram o Push), es obligatorio modificar esta clase.
 *
 * (3) PRINCIPIO SOLID VIOLADO:
 * OCP - Open/Closed Principle (Principio de Abierto/Cerrado).
 * "Las entidades de software (clases, módulos, funciones, etc.) deben estar 
 * abiertas para su extensión, pero cerradas para su modificación."
 *
 * (4) JUSTIFICACIÓN TÉCNICA:
 * El diseño actual obliga a tocar código existente y funcional (NotificationService) 
 * cada vez que hay un nuevo requerimiento (un nuevo canal de notificación).
 * Esto viola el principio OCP porque no podemos extender el comportamiento
 * del sistema sin modificar el código que ya está escrito y probado.
 * Modificar código existente aumenta el riesgo de introducir nuevos bugs.
 *
 * (5) SOLUCIÓN PROPUESTA (Refactorización):
 * Utilizar polimorfismo a través de una abstracción (interfaz). Definimos 
 * una interfaz común para todas las notificaciones. Cada canal nuevo será 
 * una nueva clase que implemente esta interfaz (extensión). El servicio 
 * simplemente delega la acción a la abstracción sin importar qué canal
 * específico se está usando (cerrado a modificación).
 */
public class Ejercicio3 {

    // --- SOLUCIÓN REFACTORIZADA ---

    /**
     * Abstracción que define el contrato para cualquier tipo de notificación.
     */
    public interface NotificationChannel {
        void send(String message);
    }

    // 1. Nuevas clases implementan la interfaz (Abierto para extensión)

    public static class EmailNotification implements NotificationChannel {
        @Override
        public void send(String message) {
            System.out.println("Enviando EMAIL: " + message);
        }
    }

    public static class SMSNotification implements NotificationChannel {
        @Override
        public void send(String message) {
            System.out.println("Enviando SMS: " + message);
        }
    }

    public static class WhatsAppNotification implements NotificationChannel {
        @Override
        public void send(String message) {
            System.out.println("Enviando WHATSAPP: " + message);
        }
    }
    


    /**
     * El servicio principal ahora depende de la abstracción, no de 
     * implementaciones concretas ni condicionales. (Cerrado para modificación)
     */
    public static class NotificationService {
        private final NotificationChannel channel;

        public NotificationService(NotificationChannel channel) {
            this.channel = channel;
        }

        public void notifyUser(String message) {
            // Delega la acción. No necesita saber si es Email, SMS, etc.
            this.channel.send(message);
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        // Configuramos el servicio con el canal deseado (ej. Email)
        NotificationChannel emailChannel = new EmailNotification();
        NotificationService emailService = new NotificationService(emailChannel);
        emailService.notifyUser("¡Bienvenido al sistema!");

        // Si luego queremos enviar por WhatsApp, usamos el mismo servicio
        // pero inyectando el canal diferente.
        NotificationChannel whatsappChannel = new WhatsAppNotification();
        NotificationService whatsappService = new NotificationService(whatsappChannel);
        whatsappService.notifyUser("Tu código de verificación es 1234");
    }
}
