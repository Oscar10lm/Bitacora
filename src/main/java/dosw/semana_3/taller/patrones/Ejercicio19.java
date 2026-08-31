package dosw.semana_3.taller.patrones;

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
