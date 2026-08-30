package dosw.semana_3.extra.patrones.estructurales.flyweight;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ============================================================================
 * PATRÓN DE DISEÑO: FLYWEIGHT (ESTRUCTURAL)
 * ============================================================================
 *
 * DEFINICIÓN:
 * Permite mantener muchos objetos en memoria compartiendo eficientemente la 
 * parte de su estado que es común entre todos ellos (el estado "intrínseco"), 
 * en vez de duplicarla en cada instancia. Solo se guarda por separado lo que 
 * varía (el estado "extrínseco").
 *
 * VENTAJAS CLAVE:
 * - Ahorro masivo de memoria RAM.
 * - Mejora de rendimiento al tener menos objetos pesados que instanciar.
 *
 * ----------------------------------------------------------------------------
 * EJERCICIO PRÁCTICO Fl1: RENDERIZADO DE UN BOSQUE
 * ----------------------------------------------------------------------------
 * Un videojuego debe dibujar miles de árboles en un bosque. Cada árbol comparte 
 * el mismo modelo 3D y textura (datos pesados, estado intrínseco), pero cada 
 * uno tiene su propia posición (x, y) en el mapa (estado extrínseco).
 */
public class ForestFlyweightSystem {

    // ==========================================
    // 1. EL FLYWEIGHT (Estado Intrínseco - Compartido y Pesado)
    // ==========================================
    // Esta clase guarda la información que NO cambia entre instancias
    public static class TreeType {
        private String name;
        private String color;
        private String textureData; // Simulamos un objeto muy pesado (MBs en memoria)

        public TreeType(String name, String color, String textureData) {
            this.name = name;
            this.color = color;
            this.textureData = textureData;
        }

        public void draw(int x, int y) {
            System.out.println("  -> Dibujando un [" + name + "] de color " + color + " en las coordenadas (" + x + ", " + y + ")");
        }
    }

    // ==========================================
    // 2. EL CONTEXTO (Estado Extrínseco - Único por cada instancia)
    // ==========================================
    // Esta es la clase ligera de la cual crearemos millones de instancias
    public static class Tree {
        private int x;
        private int y;
        private TreeType type; // Referencia al objeto pesado compartido

        public Tree(int x, int y, TreeType type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public void draw() {
            type.draw(x, y);
        }
    }

    // ==========================================
    // 3. LA FÁBRICA FLYWEIGHT (Flyweight Factory)
    // ==========================================
    // Gestiona el caché de los objetos compartidos. Evita que se creen duplicados.
    public static class TreeFactory {
        static Map<String, TreeType> treeTypes = new HashMap<>();

        public static TreeType getTreeType(String name, String color, String textureData) {
            TreeType result = treeTypes.get(name);
            
            // Si el tipo de árbol no existe en memoria, lo creamos
            if (result == null) {
                System.out.println("  [FÁBRICA]: Cargando nuevo modelo 3D pesado en la RAM -> " + name);
                result = new TreeType(name, color, textureData);
                treeTypes.put(name, result);
            }
            // Si ya existe, simplemente devolvemos la referencia en memoria
            return result;
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static class Forest {
        private List<Tree> trees = new ArrayList<>();

        public void plantTree(int x, int y, String name, String color, String textureData) {
            // Pasamos por la fábrica para reciclar memoria
            TreeType type = TreeFactory.getTreeType(name, color, textureData);
            Tree tree = new Tree(x, y, type);
            trees.add(tree);
        }

        public void draw() {
            for (Tree tree : trees) {
                tree.draw();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(">>> MOTOR GRÁFICO: CARGANDO BOSQUE (Flyweight) <<<\n");

        Forest forest = new Forest();
        
        System.out.println("--- Fase 1: Plantando 5 Pinos (Comparte mismo modelo) ---");
        forest.plantTree(10, 20, "Pino", "Verde Oscuro", "TexturaPino_AltaResolucion.png");
        forest.plantTree(15, 25, "Pino", "Verde Oscuro", "TexturaPino_AltaResolucion.png");
        forest.plantTree(10, 30, "Pino", "Verde Oscuro", "TexturaPino_AltaResolucion.png");
        forest.plantTree(12, 10, "Pino", "Verde Oscuro", "TexturaPino_AltaResolucion.png");
        forest.plantTree(5, 5, "Pino", "Verde Oscuro", "TexturaPino_AltaResolucion.png");
        
        System.out.println("\n--- Fase 2: Plantando 3 Robles (Comparte nuevo modelo) ---");
        forest.plantTree(50, 60, "Roble", "Otoño/Naranja", "TexturaRoble_HD.png");
        forest.plantTree(55, 62, "Roble", "Otoño/Naranja", "TexturaRoble_HD.png");
        forest.plantTree(51, 65, "Roble", "Otoño/Naranja", "TexturaRoble_HD.png");

        System.out.println("\n--- Fase 3: Renderizado Final ---");
        forest.draw();
        
        System.out.println("\n[RENDIMIENTO]:");
        System.out.println("Árboles plantados en el mapa (Instancias ligeras) : " + forest.trees.size());
        System.out.println("Modelos 3D cargados en la RAM (Objetos pesados)   : " + TreeFactory.treeTypes.size());
        System.out.println("¡Ahorro masivo de memoria conseguido!");
    }
}
