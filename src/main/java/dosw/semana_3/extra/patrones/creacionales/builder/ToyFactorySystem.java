package dosw.semana_3.extra.patrones.creacionales.builder;

public class ToyFactorySystem {

    // ==========================================
    // 1. EL PRODUCTO (El objeto complejo resultante)
    // ==========================================
    public static class Toy {
        private String head;
        private String body;
        private String arms;
        private String legs;
        private String accessories; // Opcional

        public void setHead(String head) { this.head = head; }
        public void setBody(String body) { this.body = body; }
        public void setArms(String arms) { this.arms = arms; }
        public void setLegs(String legs) { this.legs = legs; }
        public void setAccessories(String accessories) { this.accessories = accessories; }

        @Override
        public String toString() {
            return "Juguete Ensamblado [" +
                   "Cabeza: " + head +
                   ", Cuerpo: " + body +
                   ", Brazos: " + arms +
                   ", Piernas: " + legs +
                   (accessories != null ? ", Accesorios: " + accessories : ", Sin accesorios") +
                   "]";
        }
    }

    // ==========================================
    // 2. INTERFAZ BUILDER (Pasos comunes de construcción)
    // ==========================================
    public interface ToyBuilder {
        void reset();
        void buildHead();
        void buildBody();
        void buildArms();
        void buildLegs();
        void buildAccessories();
        Toy getResult();
    }

    // ==========================================
    // 3. CONSTRUCTORES CONCRETOS (Diferentes implementaciones)
    // ==========================================
    
    // Constructor para el Muñeco de Acción
    public static class ActionFigureBuilder implements ToyBuilder {
        private Toy result;

        public ActionFigureBuilder() {
            this.reset();
        }

        @Override
        public void reset() {
            this.result = new Toy();
        }

        @Override
        public void buildHead() {
            result.setHead("Cabeza con casco táctico");
        }

        @Override
        public void buildBody() {
            result.setBody("Cuerpo musculoso con armadura");
        }

        @Override
        public void buildArms() {
            result.setArms("Brazos articulados fuertes");
        }

        @Override
        public void buildLegs() {
            result.setLegs("Piernas con botas de combate");
        }

        @Override
        public void buildAccessories() {
            result.setAccessories("Rifle láser y escudo");
        }

        @Override
        public Toy getResult() {
            Toy builtToy = this.result;
            this.reset(); // Listo para el próximo
            return builtToy;
        }
    }

    // Constructor para la Muñeca Clásica
    public static class ClassicDollBuilder implements ToyBuilder {
        private Toy result;

        public ClassicDollBuilder() {
            this.reset();
        }

        @Override
        public void reset() {
            this.result = new Toy();
        }

        @Override
        public void buildHead() {
            result.setHead("Cabeza con cabello largo rubio");
        }

        @Override
        public void buildBody() {
            result.setBody("Cuerpo delgado con vestido de tela");
        }

        @Override
        public void buildArms() {
            result.setArms("Brazos de plástico suave");
        }

        @Override
        public void buildLegs() {
            result.setLegs("Piernas articuladas con zapatos de tacón");
        }

        @Override
        public void buildAccessories() {
            result.setAccessories("Bolso y sombrero a juego");
        }

        @Override
        public Toy getResult() {
            Toy builtToy = this.result;
            this.reset();
            return builtToy;
        }
    }

    // ==========================================
    // 4. EL DIRECTOR (Define el orden de ejecución)
    // ==========================================
    public static class ToyDirector {
        private ToyBuilder builder;

        // El cliente asocia el constructor con la clase directora
        public void setBuilder(ToyBuilder builder) {
            this.builder = builder;
        }

        // Construcción completa (Con accesorios)
        public void constructFullToy() {
            builder.buildHead();
            builder.buildBody();
            builder.buildArms();
            builder.buildLegs();
            builder.buildAccessories(); // Incluye el paso opcional
        }

        // Construcción básica (Sin accesorios)
        public void constructBasicToy() {
            builder.buildHead();
            builder.buildBody();
            builder.buildArms();
            builder.buildLegs();
            // Omite los accesorios
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        ToyDirector director = new ToyDirector();

        // 1. Construir un Muñeco de Acción completo
        System.out.println("--- Línea de Producción: Muñeco de Acción ---");
        ActionFigureBuilder actionBuilder = new ActionFigureBuilder();
        director.setBuilder(actionBuilder);
        director.constructFullToy();
        Toy actionFigure = actionBuilder.getResult();
        System.out.println(actionFigure);

        // 2. Construir una Muñeca Clásica básica (sin accesorios)
        System.out.println("\n--- Línea de Producción: Muñeca Clásica (Versión Económica) ---");
        ClassicDollBuilder dollBuilder = new ClassicDollBuilder();
        director.setBuilder(dollBuilder);
        director.constructBasicToy(); // Reusamos la lógica de construcción del Director
        Toy classicDoll = dollBuilder.getResult();
        System.out.println(classicDoll);
        
        /*
         * VENTAJA DEL DIRECTOR:
         * Hemos reusado el código del Director para construir versiones completas 
         * y versiones económicas sin tocar las clases del Builder ni del Producto.
         */
    }
}
