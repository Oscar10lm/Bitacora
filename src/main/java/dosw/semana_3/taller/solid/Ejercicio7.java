package dosw.semana_3.taller.solid;

/**
 * -------------------------------------------------------------------
 * #7 Plataforma de Streaming — Tipos de Usuario
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * Existen diferentes tipos de usuarios (Gratuito, Premium, Familiar). 
 * Todos comparten un flujo base (reproducir contenido), pero tienen 
 * capacidades adicionales completamente distintas (descargar, crear perfiles) 
 * o restricciones (límite de reproducción).
 *
 * (2) PRINCIPIO SOLID A APLICAR:
 * ISP - Interface Segregation Principle (Principio de Segregación de Interfaces).
 * (También entra en juego LSP - Principio de Sustitución de Liskov).
 *
 * (3) JUSTIFICACIÓN TÉCNICA:
 * - Aplicamos ISP: Si creamos una única y enorme clase o interfaz `User` que
 * contenga métodos como `downloadContent()` o `createSecondaryProfile()`, 
 * estaríamos obligando al `FreeUser` a implementar métodos que no puede usar, 
 * lo que resultaría en excepciones no deseadas o código vacío. Debemos 
 * segregar estas capacidades en interfaces específicas (`Downloadable`, 
 * `ProfileManageable`).
 * 
 * - Aplicamos LSP: El requerimiento dice explícitamente "Todos deben poder 
 * reproducir contenido con el mismo flujo". Esto significa que cualquier 
 * subtipo de usuario debe poder sustituir al usuario base al momento de llamar 
 * a `playContent()` sin que el sistema colapse.
 *
 * (4) SOLUCIÓN PROPUESTA (Estructura):
 * Tener una interfaz base `StreamingUser` con el método `playContent()`. 
 * Luego, crear interfaces segregadas para las funciones avanzadas. Las clases 
 * concretas implementarán las interfaces según sus capacidades reales.
 */
public class Ejercicio7 {

    // --- ESQUELETO DE SOLUCIÓN BASADO EN ISP Y LSP ---

    /**
     * Interfaz base que TODO usuario debe cumplir (LSP).
     */
    public interface StreamingUser {
        void playContent();
    }

    /**
     * Interfaces segregadas para capacidades específicas (ISP).
     */
    public interface Downloadable {
        void downloadContent();
    }

    public interface ProfileManageable {
        void createSecondaryProfile();
    }

    // --- TIPOS DE USUARIO ---

    // El usuario gratuito solo implementa lo base
    public static class FreeUser implements StreamingUser {
        @Override
        public void playContent() {
            System.out.println("Reproduciendo con anuncios y validando límite...");
        }
    }

    // El usuario premium puede reproducir y descargar
    public static class PremiumUser implements StreamingUser, Downloadable {
        @Override
        public void playContent() {
            System.out.println("Reproduciendo en 4K sin anuncios...");
        }

        @Override
        public void downloadContent() {
            System.out.println("Descargando contenido para ver offline...");
        }
    }

    // El usuario familiar puede reproducir y gestionar perfiles
    public static class FamilyUser implements StreamingUser, ProfileManageable {
        @Override
        public void playContent() {
            System.out.println("Reproduciendo contenido (Perfil principal)...");
        }

        @Override
        public void createSecondaryProfile() {
            System.out.println("Creando perfil para los niños...");
        }
    }

    // --- DEMOSTRACIÓN DEL FLUJO COMÚN (LSP) ---
    
    public static class VideoPlayer {
        // El reproductor no necesita saber qué tipo de usuario es, 
        // solo confía en que puede reproducir (LSP).
        public void startPlayback(StreamingUser user) {
            System.out.println("Iniciando buffer de video...");
            user.playContent(); 
        }
    }

    public static void main(String[] args) {
        VideoPlayer player = new VideoPlayer();
        
        StreamingUser free = new FreeUser();
        StreamingUser premium = new PremiumUser();
        
        // Ambos fluyen igual en la funcionalidad principal
        player.startPlayback(free);
        player.startPlayback(premium);
    }
}
