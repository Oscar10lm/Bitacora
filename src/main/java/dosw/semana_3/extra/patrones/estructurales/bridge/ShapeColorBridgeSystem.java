package dosw.semana_3.extra.patrones.estructurales.bridge;

/**
 * ============================================================================
 * PATRÓN DE DISEÑO: BRIDGE (ESTRUCTURAL)
 * ============================================================================
 *
 * DEFINICIÓN:
 * Es un patrón de diseño estructural que te permite dividir una clase grande, 
 * o un grupo de clases estrechamente relacionadas, en dos jerarquías separadas 
 * (abstracción e implementación) que pueden desarrollarse independientemente.
 *
 * EL PROBLEMA DE LA EXPLOSIÓN DE SUBCLASES:
 * Si tenemos la clase Forma (con hijos Círculo y Cuadrado) y queremos agregar 
 * Color, la herencia tradicional nos obliga a crear: CirculoRojo, CirculoAzul, 
 * CuadradoRojo, CuadradoAzul. Al agregar un triángulo, son 3 clases más. 
 * ¡Crece exponencialmente!
 *
 * LA SOLUCIÓN DEL PUENTE:
 * El patrón Bridge resuelve esto pasando de la herencia a la composición. 
 * Se extrae la dimensión "Color" a una jerarquía de clases separada. 
 * La clase "Forma" ahora referencia un objeto de la nueva jerarquía "Color".
 * 
 * COMPONENTES:
 * - Abstracción: Capa de control de alto nivel (Forma). Delega el trabajo real.
 * - Implementación: Interfaz común para las plataformas (Color).
 */
public class ShapeColorBridgeSystem {

    // ==========================================
    // 1. IMPLEMENTACIÓN (La Jerarquía Extraída)
    // ==========================================
    // Esta interfaz declara los métodos comunes para todas las implementaciones concretas.
    public interface Color {
        String fill();
    }

    // Implementaciones Concretas
    public static class RedColor implements Color {
        @Override
        public String fill() {
            return "Color Rojo Carmesí";
        }
    }

    public static class BlueColor implements Color {
        @Override
        public String fill() {
            return "Color Azul Océano";
        }
    }
    
    // Podemos agregar un nuevo color en el futuro sin afectar a las formas
    public static class GreenColor implements Color {
        @Override
        public String fill() {
            return "Color Verde Esmeralda";
        }
    }

    // ==========================================
    // 2. ABSTRACCIÓN (La Jerarquía Principal)
    // ==========================================
    // Ofrece la lógica de control de alto nivel y mantiene una referencia a la Implementación.
    public static abstract class Shape {
        
        // EL PUENTE (Composición en lugar de Herencia)
        protected final Color color;

        public Shape(Color color) {
            this.color = color;
        }

        // Método de alto nivel que las subclases deberán implementar
        public abstract void draw();
    }

    // ==========================================
    // 3. ABSTRACCIONES REFINADAS
    // ==========================================
    // Proporcionan variantes de la lógica de control de alto nivel.
    
    public static class Circle extends Shape {
        private final double radius;

        public Circle(Color color, double radius) {
            super(color);
            this.radius = radius;
        }

        @Override
        public void draw() {
            // Delega parte del trabajo (el pintado) al objeto de implementación (el color)
            System.out.println("Dibujando un CÍRCULO de radio " + radius + ".");
            System.out.println("Aplicando relleno: " + color.fill() + "\n");
        }
    }

    public static class Square extends Shape {
        private final double side;

        public Square(Color color, double side) {
            super(color);
            this.side = side;
        }

        @Override
        public void draw() {
            // Delega parte del trabajo (el pintado) al objeto de implementación
            System.out.println("Dibujando un CUADRADO de lado " + side + ".");
            System.out.println("Aplicando relleno: " + color.fill() + "\n");
        }
    }

    // ==========================================
    // 4. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // El cliente trabaja con abstracciones de alto nivel.
        // Las combina a su antojo sin necesidad de clases pre-compiladas como "CirculoRojo".

        System.out.println("--- Renderizando Formas con Bridge ---");
        
        // 1. Círculo Rojo
        Color red = new RedColor();
        Shape redCircle = new Circle(red, 5.5);
        redCircle.draw();

        // 2. Cuadrado Azul
        Color blue = new BlueColor();
        Shape blueSquare = new Square(blue, 10.0);
        blueSquare.draw();
        
        // 3. Cuadrado Verde (Demostrando la extensibilidad independiente)
        Color green = new GreenColor();
        Shape greenSquare = new Square(green, 4.0);
        greenSquare.draw();
        
        /*
         * VENTAJAS OBSERVADAS:
         * Si tuviéramos herencia clásica, para estas 3 formas habríamos necesitado 
         * escribir 3 clases distintas (CirculoRojo, CuadradoAzul, CuadradoVerde). 
         * Con Bridge, simplemente combinamos las 2 formas (Círculo, Cuadrado) 
         * con los 3 colores, logrando flexibilidad total sin duplicar código.
         */
    }
}
