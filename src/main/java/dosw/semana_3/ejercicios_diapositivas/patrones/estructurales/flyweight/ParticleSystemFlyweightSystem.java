package dosw.semana_3.ejercicios_diapositivas.patrones.estructurales.flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParticleSystemFlyweightSystem {

    // ==========================================
    // 1. EL FLYWEIGHT (Estado Intrínseco - Gráficos Pesados)
    // ==========================================
    public static class ParticleType {
        private String colorBase;
        private String textureSprite; // Un sprite gráfico pesado
        private int defaultSize;

        public ParticleType(String colorBase, String textureSprite, int defaultSize) {
            this.colorBase = colorBase;
            this.textureSprite = textureSprite;
            this.defaultSize = defaultSize;
        }

        public void render(int x, int y, double velocity) {
            System.out.println("  -> Partícula dibujada en (" + x + "," + y + ") moviéndose a " + velocity + "m/s [Textura: " + textureSprite + "]");
        }
    }

    // ==========================================
    // 2. EL CONTEXTO (Estado Extrínseco - Física Ligera)
    // ==========================================
    public static class Particle {
        private int x;
        private int y;
        private double velocityX;
        private double velocityY;
        private ParticleType type; // Enlace al peso pesado

        public Particle(int x, int y, double velocityX, double velocityY, ParticleType type) {
            this.x = x;
            this.y = y;
            this.velocityX = velocityX;
            this.velocityY = velocityY;
            this.type = type;
        }

        public void move() {
            // Simulamos movimiento físico simple
            this.x += (int) velocityX;
            this.y += (int) velocityY;
            type.render(x, y, Math.abs(velocityX + velocityY)); // Se apoya en el flyweight para dibujar
        }
    }

    // ==========================================
    // 3. LA FÁBRICA FLYWEIGHT
    // ==========================================
    public static class ParticleFactory {
        static Map<String, ParticleType> particleTypes = new HashMap<>();

        public static ParticleType getParticleType(String color, String texture, int size) {
            String key = texture + "_" + color;
            ParticleType result = particleTypes.get(key);
            
            if (result == null) {
                System.out.println("  [VRAM]: Cargando textura gráfica a memoria de video -> " + texture);
                result = new ParticleType(color, texture, size);
                particleTypes.put(key, result);
            }
            return result;
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static class ExplosionSystem {
        private List<Particle> particles = new ArrayList<>();

        public void createExplosion(int startX, int startY, int particleCount, String color, String texture) {
            ParticleType type = ParticleFactory.getParticleType(color, texture, 10);
            
            for (int i = 0; i < particleCount; i++) {
                // Generamos velocidades aleatorias para simular el esparcimiento
                double velX = (Math.random() * 10) - 5;
                double velY = (Math.random() * 10) - 5;
                
                particles.add(new Particle(startX, startY, velX, velY, type));
            }
        }

        public void updatePhysics() {
            System.out.println("--- Actualizando frame físico ---");
            for (Particle p : particles) {
                p.move();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(">>> MOTOR DE FÍSICAS (Flyweight) <<<\n");

        ExplosionSystem engine = new ExplosionSystem();

        System.out.println("--- Disparo de Escopeta (Fuego) ---");
        engine.createExplosion(100, 100, 5, "Naranja", "FireSprite.png");

        System.out.println("\n--- Impacto Mágico (Hielo) ---");
        engine.createExplosion(300, 200, 3, "Azul Celeste", "IceSparkleSprite.png");

        System.out.println("\n--- Simulando el paso de 1 segundo de tiempo ---");
        engine.updatePhysics();
        
        System.out.println("\n[RENDIMIENTO]:");
        System.out.println("Total de partículas calculadas : 8 (Objetos ultraligeros de física)");
        System.out.println("Texturas en Memoria de Video   : " + ParticleFactory.particleTypes.size() + " (Sprites pesados)");
    }
}
