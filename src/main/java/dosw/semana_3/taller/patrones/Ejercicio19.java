package dosw.semana_3.taller.patrones;

/**
 * -------------------------------------------------------------------
 * PARTE III — IDENTIFICANDO PATRONES DE DISEÑO
 * #19 CASO DESAFÍO: DOSW Streaming
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * Una plataforma de streaming compleja que involucra recomendaciones, tipos 
 * de usuario, algoritmos de búsqueda, notificaciones multicanal e 
 * integraciones de terceros.
 *
 * (2) ANÁLISIS DE PRINCIPIOS SOLID A CONSIDERAR:
 * - SRP (Responsabilidad Única): 
 *   El motor de recomendaciones, el buscador, el reproductor y el facturador 
 *   deben ser módulos completamente separados. Ninguna "Clase Dios" debe 
 *   orquestar todo esto.
 * - OCP (Abierto/Cerrado): 
 *   El sistema debe estar preparado para que mañana se agregue el algoritmo 
 *   de búsqueda "Por Tendencias en Redes" sin tener que modificar la lógica 
 *   del buscador principal.
 * - LSP e ISP (Sustitución de Liskov y Segregación de Interfaces): 
 *   Vital para los Tipos de Usuarios. Un usuario gratuito no debe verse 
 *   obligado a implementar métodos de `descargarVideo()` (ISP), pero todos 
 *   deben poder pasarse al reproductor de video sin que este falle (LSP).
 * - DIP (Inversión de Dependencias): 
 *   El núcleo de la aplicación de streaming jamás debe depender directamente 
 *   de "PayU" o "Stripe". Debe depender de una interfaz `PaymentGateway`.
 *
 * (3) ANÁLISIS DE PATRONES DE DISEÑO A CONSIDERAR:
 * 1. Algoritmos de Búsqueda -> **Strategy**: 
 *    Encapsular las búsquedas (popularidad, relevancia) en distintas clases 
 *    (Estrategias) y permitir que el usuario las seleccione en tiempo de 
 *    ejecución.
 * 2. Notificaciones Multicanal -> **Observer**: 
 *    Cuando sale un nuevo episodio, el sistema notifica. Los canales (Push, 
 *    Email) son "Observadores" suscritos a ese evento. 
 * 3. Integraciones Externas -> **Adapter / Facade**: 
 *    Utilizar Adaptadores para conectar las APIs raras de subtítulos externos 
 *    con nuestra interfaz local, y Fachadas para simplificar procesos complejos 
 *    de pasarelas de pago.
 * 4. Tipos de Usuario Complejos -> **Factory Method**: 
 *    Para crear el perfil y las configuraciones de un nuevo usuario en el 
 *    registro según el plan que haya pagado.
 */
public class Ejercicio19 {

    // --- ESQUELETO ARQUITECTÓNICO DE ALTO NIVEL ---

    // 1. STRATEGY (Buscador abierto a extensión - OCP)
    public interface SearchStrategy {
        void search(String query);
    }
    public static class PopularitySearch implements SearchStrategy {
        public void search(String query) { System.out.println("Buscando por más vistos..."); }
    }
    public static class RelevanceSearch implements SearchStrategy {
        public void search(String query) { System.out.println("Buscando por coincidencia exacta..."); }
    }

    // 2. OBSERVER (Notificaciones desacopladas - SRP y OCP)
    public interface Subscriber {
        void update(String message);
    }
    public static class PushNotifier implements Subscriber {
        public void update(String msg) { System.out.println("Enviando PUSH al celular: " + msg); }
    }
    public static class EmailNotifier implements Subscriber {
        public void update(String msg) { System.out.println("Enviando EMAIL: " + msg); }
    }

    // 3. ADAPTER (Integración externa limpia - DIP)
    public interface SubtitleService {
        String getSubtitles(String videoId);
    }
    // Una API de un tercero (Código que no podemos tocar)
    public static class ExternalCrazySubtitleAPI {
        public String fetchXMLSubsByHash(String hash) { return "<subs>Texto</subs>"; }
    }
    // Nuestro adaptador
    public static class SubtitleAdapter implements SubtitleService {
        private ExternalCrazySubtitleAPI api = new ExternalCrazySubtitleAPI();
        public String getSubtitles(String videoId) {
            return api.fetchXMLSubsByHash(videoId); // Traducción
        }
    }

    // 4. ISP & LSP (Usuarios segregados)
    public interface Playable { void play(); }
    public interface Downloadable { void download(); }

    public static class PremiumUser implements Playable, Downloadable {
        public void play() { System.out.println("Premium: Jugando en 4K..."); }
        public void download() { System.out.println("Premium: Descargando..."); }
    }
    public static class FreeUser implements Playable {
        public void play() { System.out.println("Gratis: Jugando con 3 anuncios..."); }
    }

    // --- DEMOSTRACIÓN DEL CÓDIGO CLIENTE LIMPIO ---
    public static class StreamingApp {
        private SearchStrategy searchEngine;
        
        public void setSearchEngine(SearchStrategy searchEngine) {
            this.searchEngine = searchEngine;
        }

        public void performSearch(String query) {
            searchEngine.search(query);
        }
        
        public void playContent(Playable user) {
            user.play();
        }
    }

    public static void main(String[] args) {
        StreamingApp netflixClone = new StreamingApp();
        
        // El usuario elige ordenar por popularidad
        netflixClone.setSearchEngine(new PopularitySearch());
        netflixClone.performSearch("Inception");

        // El reproductor no necesita saber qué tipo de usuario es (LSP)
        netflixClone.playContent(new PremiumUser());
        netflixClone.playContent(new FreeUser());
    }
}
