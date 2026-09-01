package dosw.semana_3.ejercicios_diapositivas.patrones.comportamiento.memento;

public class VideoGameMementoSystem {

    // ==========================================
    // 1. EL MEMENTO (El Archivo de Guardado / Checkpoint)
    // ==========================================
    public static class GameCheckpoint {
        private final int savedHealth;
        private final String savedLevel;
        private final String savedInventory;

        public GameCheckpoint(int health, String level, String inventory) {
            this.savedHealth = health;
            this.savedLevel = level;
            this.savedInventory = inventory;
        }

        private int getHealth() { return savedHealth; }
        private String getLevel() { return savedLevel; }
        private String getInventory() { return savedInventory; }
    }

    // ==========================================
    // 2. EL ORIGINADOR (El Jugador / El Motor del Juego)
    // ==========================================
    public static class Player {
        private int health;
        private String currentLevel;
        private String inventory;

        public Player() {
            this.health = 100;
            this.currentLevel = "1-1 (Bosque Inicial)";
            this.inventory = "Espada Rota";
        }

        // Simulador de Gameplay
        public void play(String level, String loot, int damageTaken) {
            this.currentLevel = level;
            this.inventory = this.inventory + ", " + loot;
            this.health -= damageTaken;
            System.out.println(" [JUGANDO] Avanza a " + currentLevel + " | Recibe: " + loot + " | Vida: " + health + "%");
        }
        
        public void showStatus() {
            System.out.println("   -> ESTADO ACTUAL | Nivel: " + currentLevel + " | Vida: " + health + "% | Inv: " + inventory);
        }

        // CREAR MEMENTO
        public GameCheckpoint saveProgress() {
            System.out.println("\n(O) [SISTEMA]: GUARDANDO PARTIDA...");
            return new GameCheckpoint(health, currentLevel, inventory);
        }

        // RESTAURAR MEMENTO
        public void reloadProgress(GameCheckpoint checkpoint) {
            System.out.println("\n(X) [SISTEMA]: JUGADOR MUERTO. CERRANDO PANTALLA ROJA...");
            System.out.println("    [SISTEMA]: CARGANDO ÚLTIMO CHECKPOINT...");
            this.health = checkpoint.getHealth();
            this.currentLevel = checkpoint.getLevel();
            this.inventory = checkpoint.getInventory();
        }
    }

    // ==========================================
    // 3. EL CUIDADOR (El Gestor de Memoria Card/Disco Duro)
    // ==========================================
    public static class SaveManager {
        // En este caso solo guardamos 1 slot (Quick Save)
        private GameCheckpoint quickSaveSlot;

        public void saveGame(Player player) {
            quickSaveSlot = player.saveProgress();
        }

        public void loadGame(Player player) {
            if (quickSaveSlot != null) {
                player.reloadProgress(quickSaveSlot);
            } else {
                System.out.println("Error: No hay partida guardada.");
            }
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        System.out.println(">>> DARK SOULS CLON - INICIANDO JUEGO <<<\n");
        
        Player hero = new Player();
        SaveManager memoryCard = new SaveManager();
        
        hero.showStatus();

        // Juega el nivel 1
        hero.play("1-2 (Puente Viejo)", "Escudo de Madera", 20);
        hero.showStatus();
        
        // Encuentra una hoguera y GUARDA LA PARTIDA
        memoryCard.saveGame(hero);

        // Se enfrenta al Jefe Final
        System.out.println("\n--- Entrando a la zona del Jefe Final ---");
        hero.play("1-3 (Guarida del Dragón)", "Llave Dorada", 80);
        hero.showStatus(); // Queda en 0% de vida

        // Muere y CARGA LA PARTIDA
        memoryCard.loadGame(hero);
        hero.showStatus(); // Vuelve al puente viejo, con 80% de vida y sin la llave.
    }
}
