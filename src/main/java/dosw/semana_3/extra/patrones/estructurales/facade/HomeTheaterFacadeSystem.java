package dosw.semana_3.extra.patrones.estructurales.facade;

/**
 * ============================================================================
 * PATRÓN DE DISEÑO: FACADE (ESTRUCTURAL)
 * ============================================================================
 *
 * DEFINICIÓN:
 * Proporciona una interfaz simple y unificada para un subsistema complejo 
 * (con muchas clases e interacciones internas), ocultando esa complejidad al 
 * cliente. 
 *
 * VENTAJAS CLAVE:
 * - Aísla a los clientes de los componentes del subsistema complejo.
 * - Reduce el número de objetos que los clientes manejan.
 * - Promueve el bajo acoplamiento entre los subsistemas y los clientes.
 *
 * ----------------------------------------------------------------------------
 * EJERCICIO PRÁCTICO F1: CINE EN CASA (Home Theater)
 * ----------------------------------------------------------------------------
 * Encender un sistema de "home theater" implica coordinar el proyector, 
 * las luces, el sistema de sonido y el reproductor de streaming.
 * La Fachada ofrece un método simple verPelicula() que internamente 
 * coordina todo este subsistema.
 */
public class HomeTheaterFacadeSystem {

    // ==========================================
    // 1. EL SUBSISTEMA COMPLEJO (Componentes individuales)
    // ==========================================
    
    public static class Projector {
        public void turnOn() { System.out.println("  [Proyector]: Encendido y calentando lámpara."); }
        public void setWideScreenMode() { System.out.println("  [Proyector]: Modo pantalla ancha (16:9) activado."); }
        public void turnOff() { System.out.println("  [Proyector]: Apagado."); }
    }

    public static class Lights {
        public void dim(int level) { System.out.println("  [Luces]: Atenuando al " + level + "%."); }
        public void turnOn() { System.out.println("  [Luces]: Encendidas al 100%."); }
    }

    public static class SoundSystem {
        public void turnOn() { System.out.println("  [Sonido]: Encendido."); }
        public void setVolume(int level) { System.out.println("  [Sonido]: Volumen ajustado al " + level + "."); }
        public void setSurroundMode() { System.out.println("  [Sonido]: Modo Dolby Atmos Surround activado."); }
        public void turnOff() { System.out.println("  [Sonido]: Apagado."); }
    }

    public static class StreamingPlayer {
        public void turnOn() { System.out.println("  [Reproductor]: Encendido (Apple TV)."); }
        public void play(String movie) { System.out.println("  [Reproductor]: Reproduciendo película '" + movie + "'."); }
        public void turnOff() { System.out.println("  [Reproductor]: Apagado."); }
    }

    // ==========================================
    // 2. LA FACHADA (Facade)
    // ==========================================
    public static class HomeTheaterFacade {
        private Projector projector;
        private Lights lights;
        private SoundSystem sound;
        private StreamingPlayer player;

        // La fachada inicializa e inyecta las dependencias complejas
        public HomeTheaterFacade(Projector projector, Lights lights, SoundSystem sound, StreamingPlayer player) {
            this.projector = projector;
            this.lights = lights;
            this.sound = sound;
            this.player = player;
        }

        // El cliente solo llama a este método simple
        public void watchMovie(String movie) {
            System.out.println(">>> Preparando el sistema para ver una película... <<<");
            lights.dim(10);
            projector.turnOn();
            projector.setWideScreenMode();
            sound.turnOn();
            sound.setSurroundMode();
            sound.setVolume(25);
            player.turnOn();
            player.play(movie);
            System.out.println(">>> ¡Todo listo! Disfrute la función. <<<\n");
        }

        public void endMovie() {
            System.out.println(">>> Apagando el sistema de Cine en Casa... <<<");
            lights.turnOn();
            player.turnOff();
            sound.turnOff();
            projector.turnOff();
            System.out.println(">>> Sistema apagado. <<<\n");
        }
    }

    // ==========================================
    // 3. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        // En la vida real, un framework de inyección (como Spring) crearía 
        // estos componentes y armaría la fachada, para que el cliente no tenga ni que verlos.
        Projector projector = new Projector();
        Lights lights = new Lights();
        SoundSystem sound = new SoundSystem();
        StreamingPlayer player = new StreamingPlayer();

        HomeTheaterFacade smartRemote = new HomeTheaterFacade(projector, lights, sound, player);

        System.out.println("--- El usuario presiona UN SOLO BOTÓN ('Ver Película') en su celular ---");
        // El cliente (usuario) no tiene que interactuar con los 4 componentes por separado
        smartRemote.watchMovie("Inception (El Origen)");
        
        System.out.println("--- El usuario presiona UN SOLO BOTÓN ('Apagar Todo') ---");
        smartRemote.endMovie();
    }
}
