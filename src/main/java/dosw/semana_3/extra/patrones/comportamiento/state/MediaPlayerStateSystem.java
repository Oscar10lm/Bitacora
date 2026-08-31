package dosw.semana_3.extra.patrones.comportamiento.state;

public class MediaPlayerStateSystem {

    // ==========================================
    // 1. EL CONTEXTO (El Reproductor VLC)
    // ==========================================
    public static class MediaPlayer {
        private PlayerState state;

        public MediaPlayer() {
            // Estado inicial de la UI al cargar un video
            this.state = new StoppedState();
        }

        public void changeState(PlayerState state) {
            this.state = state;
        }

        // El control GUI: El reproductor no piensa, solo avisa a su estado
        public void clickPlayPause() {
            state.pressPlayPause(this);
        }

        public void clickStop() {
            state.pressStop(this);
        }
    }

    // ==========================================
    // 2. LA INTERFAZ ESTADO
    // ==========================================
    public interface PlayerState {
        void pressPlayPause(MediaPlayer context);
        void pressStop(MediaPlayer context);
    }

    // ==========================================
    // 3. ESTADOS CONCRETOS
    // ==========================================
    
    // ESTADO: Detenido (Como al arrancar o al darle stop)
    public static class StoppedState implements PlayerState {
        @Override
        public void pressPlayPause(MediaPlayer context) {
            System.out.println("[STOPPED] -> Iniciando reproducción desde el principio (00:00). Cambiando a PLAYING.");
            context.changeState(new PlayingState());
        }

        @Override
        public void pressStop(MediaPlayer context) {
            System.out.println("[STOPPED] -> Ya estaba detenido. No hace nada.");
        }
    }

    // ESTADO: Reproduciendo video activo
    public static class PlayingState implements PlayerState {
        @Override
        public void pressPlayPause(MediaPlayer context) {
            System.out.println("[PLAYING] -> Congelando la imagen. Cambiando a PAUSED.");
            context.changeState(new PausedState());
        }

        @Override
        public void pressStop(MediaPlayer context) {
            System.out.println("[PLAYING] -> Cerrando flujo de video y rebobinando a 00:00. Cambiando a STOPPED.");
            context.changeState(new StoppedState());
        }
    }

    // ESTADO: Pausado
    public static class PausedState implements PlayerState {
        @Override
        public void pressPlayPause(MediaPlayer context) {
            System.out.println("[PAUSED] -> Reanudando video desde el cuadro congelado. Cambiando a PLAYING.");
            context.changeState(new PlayingState());
        }

        @Override
        public void pressStop(MediaPlayer context) {
            System.out.println("[PAUSED] -> Cerrando flujo de video y rebobinando a 00:00. Cambiando a STOPPED.");
            context.changeState(new StoppedState());
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        System.out.println(">>> ABRIENDO VLC MEDIA PLAYER <<<\n");
        
        MediaPlayer player = new MediaPlayer();
        
        // 1. Damos Play (Estaba en Stopped, arranca desde 00:00)
        System.out.println("--- Usuario hace clic en el botón 'Play/Pause' ---");
        player.clickPlayPause(); 
        
        // 2. Damos Pause (Estaba Playing, ahora se pausa)
        System.out.println("\n--- Usuario hace clic en el botón 'Play/Pause' de nuevo ---");
        player.clickPlayPause();
        
        // 3. Quitamos el Pause (Estaba Paused, ahora reanuda)
        System.out.println("\n--- Usuario hace clic en el botón 'Play/Pause' otra vez ---");
        player.clickPlayPause();
        
        // 4. Detenemos (Estaba Playing, ahora se rebobina a 0)
        System.out.println("\n--- Usuario hace clic en el botón cuadrado 'Stop' ---");
        player.clickStop();
        
        // 5. Play de nuevo (Arranca desde 00:00 de nuevo)
        System.out.println("\n--- Usuario hace clic en el botón 'Play/Pause' ---");
        player.clickPlayPause();
    }
}
