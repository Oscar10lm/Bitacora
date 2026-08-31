package dosw.semana_3.extra.patrones.creacionales.prototype;

import java.util.ArrayList;
import java.util.List;

public class VideoGameEnemySystem {

    // ==========================================
    // 1. INTERFAZ PROTOTIPO
    // ==========================================
    public interface EnemyPrototype {
        EnemyPrototype cloneEnemy();
    }

    // ==========================================
    // 2. PROTOTIPO CONCRETO (El Orco)
    // ==========================================
    public static class Orc implements EnemyPrototype {
        
        // Atributos "pesados" (calculados)
        private int health;
        private int attack;
        private int defense;
        private String aiBehaviorTree;
        
        // Atributos "ligeros" o cambiantes por instancia
        private int positionX;
        private int positionY;

        // Constructor estándar (Representa cálculos pesados basados en nivel de dificultad)
        public Orc(int playerLevel) {
            System.out.println(">>> [COSTOSO] Calculando Stats y cargando IA del Orco (Nivel Jugador: " + playerLevel + ")...");
            simulateHeavyMath(); // Simula el bloqueo
            
            // Fórmulas complejas ficticias
            this.health = playerLevel * 150;
            this.attack = playerLevel * 12;
            this.defense = playerLevel * 5;
            this.aiBehaviorTree = "Aggressive_Flanker_Tree_v2.json";
            
            this.positionX = 0;
            this.positionY = 0;
            System.out.println("    Orco Base cargado en memoria.\n");
        }

        // Constructor privado (Copia instantánea de los valores en memoria)
        private Orc(Orc target) {
            if (target != null) {
                this.health = target.health;
                this.attack = target.attack;
                this.defense = target.defense;
                this.aiBehaviorTree = target.aiBehaviorTree;
                
                // Las posiciones se copian por defecto, aunque el cliente las sobreescribirá
                this.positionX = target.positionX;
                this.positionY = target.positionY;
            }
        }

        private void simulateHeavyMath() {
            try {
                Thread.sleep(2000); // 2 segundos de carga por los cálculos de IA y stats
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // ==========================================
        // 3. IMPLEMENTACIÓN DE LA CLONACIÓN
        // ==========================================
        @Override
        public EnemyPrototype cloneEnemy() {
            return new Orc(this);
        }

        // Setters para mutar el clon después de crearlo
        public void setPosition(int x, int y) {
            this.positionX = x;
            this.positionY = y;
        }

        @Override
        public String toString() {
            return String.format("Orco en [%d, %d] -> Vida: %d | Atq: %d | Def: %d | IA: %s", 
                                 positionX, positionY, health, attack, defense, aiBehaviorTree);
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // 1. CARGA DE NIVEL
        System.out.println("--- Cargando Nivel 5 ---");
        int playerLevel = 5;
        
        // Creamos el Orco Prototipo (esto tarda 2 segundos)
        Orc orcPrototype = new Orc(playerLevel);

        // 2. SPAWN DE LA OLEADA (Wave)
        System.out.println("--- ¡Oleada Enemiga Detectada! Spawneando 10 Orcos ---");
        List<Orc> enemyWave = new ArrayList<>();
        
        long startTime = System.currentTimeMillis();
        
        for (int i = 0; i < 10; i++) {
            // Clonamos el prototipo en vez de usar 'new Orc(5)' (que tardaría 20 segundos total)
            Orc clonedOrc = (Orc) orcPrototype.cloneEnemy();
            
            // Mutamos pequeños detalles: Asignamos posiciones distintas
            clonedOrc.setPosition(i * 10, i * 5); // Distribuidos en el mapa
            
            enemyWave.add(clonedOrc);
        }
        
        long endTime = System.currentTimeMillis();
        System.out.println("Oleada de 10 orcos spawneada en " + (endTime - startTime) + " milisegundos.");

        // 3. Verificamos el resultado
        for (Orc o : enemyWave) {
            System.out.println(o);
        }
    }
}
