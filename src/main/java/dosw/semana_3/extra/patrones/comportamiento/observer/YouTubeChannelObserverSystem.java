package dosw.semana_3.extra.patrones.comportamiento.observer;

import java.util.ArrayList;
import java.util.List;

public class YouTubeChannelObserverSystem {

    // ==========================================
    // 1. LA INTERFAZ OBSERVADOR (Subscriber)
    // ==========================================
    // Define el método de actualización que el Sujeto llamará.
    public interface Subscriber {
        void update(String channelName, String videoTitle);
    }

    // ==========================================
    // 2. OBSERVADORES CONCRETOS (Tipos de suscriptores)
    // ==========================================
    
    // Suscriptor que recibe correos electrónicos
    public static class EmailSubscriber implements Subscriber {
        private String emailAddress;

        public EmailSubscriber(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        @Override
        public void update(String channelName, String videoTitle) {
            System.out.println("[EMAIL enviado a " + emailAddress + "]: ¡Hola! El canal '" 
                               + channelName + "' acaba de subir un nuevo video: '" + videoTitle + "'");
        }
    }

    // Suscriptor que recibe notificaciones push en el celular (la campanita)
    public static class AppPushSubscriber implements Subscriber {
        private String deviceName;

        public AppPushSubscriber(String deviceName) {
            this.deviceName = deviceName;
        }

        @Override
        public void update(String channelName, String videoTitle) {
            System.out.println("[PUSH en dispositivo " + deviceName + "]: \uD83D\uDD14 Nuevo video de " 
                               + channelName + ": " + videoTitle);
        }
    }

    // ==========================================
    // 3. LA INTERFAZ SUJETO (Publisher / Observable)
    // ==========================================
    // Declara los métodos de gestión de suscriptores
    public interface YouTubeChannelPublisher {
        void subscribe(Subscriber subscriber);
        void unsubscribe(Subscriber subscriber);
        void notifySubscribers(String videoTitle);
    }

    // ==========================================
    // 4. SUJETO CONCRETO (El Canal de YouTube real)
    // ==========================================
    public static class YouTubeChannel implements YouTubeChannelPublisher {
        
        private String channelName;
        // La lista de interesados (desacoplada a través de la interfaz)
        private List<Subscriber> subscribers = new ArrayList<>();

        public YouTubeChannel(String channelName) {
            this.channelName = channelName;
        }

        @Override
        public void subscribe(Subscriber subscriber) {
            subscribers.add(subscriber);
            System.out.println(" -> Un nuevo suscriptor se ha unido a '" + channelName + "'");
        }

        @Override
        public void unsubscribe(Subscriber subscriber) {
            subscribers.remove(subscriber);
            System.out.println(" -> Un suscriptor ha dejado el canal '" + channelName + "'");
        }

        @Override
        public void notifySubscribers(String videoTitle) {
            System.out.println("\n*** '" + channelName + "' está notificando a " 
                               + subscribers.size() + " suscriptores sobre el nuevo video ***");
            // El ciclo mágico: avisa a todos sin importarle quiénes son o qué hacen con la info
            for (Subscriber sub : subscribers) {
                sub.update(this.channelName, videoTitle);
            }
            System.out.println("*************************************************************\n");
        }

        // Lógica de negocio principal de la clase
        public void uploadVideo(String videoTitle) {
            System.out.println("\n[SISTEMA]: El canal '" + channelName + "' está subiendo el video: " + videoTitle);
            System.out.println("[SISTEMA]: Procesamiento y renderizado en HD completo...");
            
            // Tan pronto cambia el estado (video subido), desencadena la notificación
            notifySubscribers(videoTitle);
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> PLATAFORMA DE VIDEOS INICIADA <<<");

        // 1. Creamos el publicador (Sujeto)
        YouTubeChannel techChannel = new YouTubeChannel("TechReviews 2026");

        // 2. Creamos algunos observadores concretos (Suscriptores)
        Subscriber mailSub1 = new EmailSubscriber("camilo@correo.com");
        Subscriber mailSub2 = new EmailSubscriber("empresa@dominio.com");
        Subscriber pushSub1 = new AppPushSubscriber("iPhone 15 Pro");
        Subscriber pushSub2 = new AppPushSubscriber("Tablet Samsung Galaxy");

        System.out.println("\n--- Los usuarios se suscriben al canal ---");
        // 3. Los observadores se suscriben al publicador
        techChannel.subscribe(mailSub1);
        techChannel.subscribe(mailSub2);
        techChannel.subscribe(pushSub1);

        // 4. El Sujeto cambia de estado (Sube un video)
        // Esto automáticamente disparará 3 notificaciones a las implementaciones concretas
        techChannel.uploadVideo("Review del nuevo Antigravity AI 2.0");

        System.out.println("--- Cambios en las suscripciones ---");
        // 5. Suscripción dinámica: Alguien se va, alguien nuevo llega
        techChannel.unsubscribe(mailSub2);     // La empresa cancela suscripción
        techChannel.subscribe(pushSub2);       // El usuario de la tablet se suscribe ("activa la campanita")

        // 6. El Sujeto vuelve a cambiar de estado
        // Esta vez el correo de la empresa no llegará, pero la tablet sí recibirá el PUSH
        techChannel.uploadVideo("Top 5 Patrones de Diseño Estructurales");
    }
}
