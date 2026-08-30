package dosw.semana_3.extra.patrones.creacionales.builder;

import java.util.ArrayList;
import java.util.List;

/**
 * ============================================================================
 * EJERCICIO PRÁCTICO: ARMADO DE PIZZAS (Builder)
 * ============================================================================
 *
 * Una pizzería arma pizzas a pedido. El proceso de preparación es el mismo 
 * (masa, salsa, ingredientes extra), pero el resultado final varía según el 
 * tipo de pizza solicitada.
 * Ejemplos: Pizza Margarita, Pizza Especial de la casa.
 * 
 * La pizzería quiere separar el proceso de construcción del objeto final.
 * 
 * Cada pizza está conformada por: 
 * - Tamaño
 * - Tipo de masa
 * - Salsa
 * - Ingredientes adicionales (lista: queso extra, pepperoni, champiñones, etc.)
 */
public class PizzaBuilderSystem {

    // ==========================================
    // 1. EL PRODUCTO (El objeto complejo resultante)
    // ==========================================
    public static class Pizza {
        private String size;
        private String dough;
        private String sauce;
        private List<String> extras = new ArrayList<>();
        private boolean isBaked;

        public void setSize(String size) { this.size = size; }
        public void setDough(String dough) { this.dough = dough; }
        public void setSauce(String sauce) { this.sauce = sauce; }
        public void addExtra(String extra) { this.extras.add(extra); }
        public void setBaked(boolean isBaked) { this.isBaked = isBaked; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Pizza [").append(size).append("] - Masa: ").append(dough)
              .append(" | Salsa: ").append(sauce);
              
            if (!extras.isEmpty()) {
                sb.append(" | Extras: ").append(String.join(", ", extras));
            } else {
                sb.append(" | Sin extras");
            }
            sb.append(" | Estado: ").append(isBaked ? "Horneada y lista" : "Cruda");
            return sb.toString();
        }
    }

    // ==========================================
    // 2. INTERFAZ BUILDER (Pasos comunes de preparación)
    // ==========================================
    public interface PizzaBuilder {
        void reset();
        void buildSize();
        void buildDough();
        void buildSauce();
        void buildExtras();
        Pizza getResult();
    }

    // ==========================================
    // 3. CONSTRUCTORES CONCRETOS
    // ==========================================
    
    // Constructor para la Pizza Margarita Clásica
    public static class MargaritaPizzaBuilder implements PizzaBuilder {
        private Pizza result;

        public MargaritaPizzaBuilder() {
            this.reset();
        }

        @Override
        public void reset() {
            this.result = new Pizza();
        }

        @Override
        public void buildSize() {
            result.setSize("Mediana");
        }

        @Override
        public void buildDough() {
            result.setDough("Masa Fina Tradicional");
        }

        @Override
        public void buildSauce() {
            result.setSauce("Salsa de Tomate San Marzano");
        }

        @Override
        public void buildExtras() {
            // La Margarita clásica es simple: solo queso extra (mozzarella fresca) y albahaca
            result.addExtra("Queso Mozzarella Fresco");
            result.addExtra("Hojas de Albahaca");
        }

        @Override
        public Pizza getResult() {
            Pizza builtPizza = this.result;
            this.reset();
            return builtPizza;
        }
    }

    // Constructor para la Pizza Especial de la Casa
    public static class SpecialPizzaBuilder implements PizzaBuilder {
        private Pizza result;

        public SpecialPizzaBuilder() {
            this.reset();
        }

        @Override
        public void reset() {
            this.result = new Pizza();
        }

        @Override
        public void buildSize() {
            result.setSize("Familiar (Grande)");
        }

        @Override
        public void buildDough() {
            result.setDough("Masa Gruesa con bordes de queso");
        }

        @Override
        public void buildSauce() {
            result.setSauce("Salsa de Tomate Picante");
        }

        @Override
        public void buildExtras() {
            result.addExtra("Doble Queso");
            result.addExtra("Pepperoni");
            result.addExtra("Champiñones");
            result.addExtra("Aceitunas Negras");
            result.addExtra("Jamón");
        }

        @Override
        public Pizza getResult() {
            Pizza builtPizza = this.result;
            this.reset();
            return builtPizza;
        }
    }

    // ==========================================
    // 4. EL DIRECTOR (El Pizzero que define el flujo)
    // ==========================================
    public static class PizzaDirector {
        private PizzaBuilder builder;

        public void setBuilder(PizzaBuilder builder) {
            this.builder = builder;
        }

        // Flujo normal de preparación de la pizza
        public void makePizza() {
            builder.buildSize();
            builder.buildDough();
            builder.buildSauce();
            builder.buildExtras();
            
            // El horneado es parte del proceso estándar orquestado por el director
            System.out.println("Preparación finalizada. Ingresando al horno...");
            // Como el horneado afecta a la pizza (y el director no retorna la pizza, lo hace el builder)
            // simulamos este paso asumiendo que el cliente la hornea, o que el propio builder la retorna horneada.
            // Para mantener la lógica limpia, el Director solo orquesta pasos de construcción.
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        PizzaDirector pizzero = new PizzaDirector();

        // 1. Preparando una Margarita
        System.out.println("--- Orden #1: Pizza Margarita ---");
        PizzaBuilder margaritaBuilder = new MargaritaPizzaBuilder();
        pizzero.setBuilder(margaritaBuilder);
        pizzero.makePizza();
        
        Pizza orden1 = margaritaBuilder.getResult();
        orden1.setBaked(true); // Termina el proceso
        System.out.println(orden1);

        // 2. Preparando la Especial
        System.out.println("\n--- Orden #2: Especial de la Casa ---");
        PizzaBuilder specialBuilder = new SpecialPizzaBuilder();
        pizzero.setBuilder(specialBuilder);
        pizzero.makePizza();
        
        Pizza orden2 = specialBuilder.getResult();
        orden2.setBaked(true);
        System.out.println(orden2);
    }
}
