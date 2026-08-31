package dosw.semana_3.extra.patrones.creacionales.abstract_factory;

public class OfficeFurnitureSystem {

    // ==========================================
    // 1. PRODUCTOS ABSTRACTOS (Interfaces de la familia)
    // ==========================================
    public interface Chair {
        void sitOn();
    }

    public interface Table {
        void use();
    }

    public interface Sofa {
        void lieOn();
    }

    // ==========================================
    // 2. PRODUCTOS CONCRETOS - FAMILIA MODERNA
    // ==========================================
    public static class ModernChair implements Chair {
        @Override
        public void sitOn() {
            System.out.println("Sentándose en una Silla Moderna de líneas rectas y acero.");
        }
    }

    public static class ModernTable implements Table {
        @Override
        public void use() {
            System.out.println("Apoyando cosas en una Mesa Moderna de vidrio templado.");
        }
    }

    public static class ModernSofa implements Sofa {
        @Override
        public void lieOn() {
            System.out.println("Recostándose en un Sofá Moderno minimalista de cuero negro.");
        }
    }

    // ==========================================
    // 3. PRODUCTOS CONCRETOS - FAMILIA VICTORIANA
    // ==========================================
    public static class VictorianChair implements Chair {
        @Override
        public void sitOn() {
            System.out.println("Sentándose en una Silla Victoriana con tallados de madera y terciopelo.");
        }
    }

    public static class VictorianTable implements Table {
        @Override
        public void use() {
            System.out.println("Apoyando cosas en una Mesa Victoriana de roble macizo y patas curvas.");
        }
    }

    public static class VictorianSofa implements Sofa {
        @Override
        public void lieOn() {
            System.out.println("Recostándose en un Sofá Victoriano abotonado tipo Chesterfield.");
        }
    }

    // ==========================================
    // 4. FÁBRICA ABSTRACTA (La interfaz creadora de familias)
    // ==========================================
    public interface FurnitureFactory {
        Chair createChair();
        Table createTable();
        Sofa createSofa();
    }

    // ==========================================
    // 5. FÁBRICAS CONCRETAS
    // ==========================================
    public static class ModernFurnitureFactory implements FurnitureFactory {
        @Override
        public Chair createChair() {
            return new ModernChair();
        }

        @Override
        public Table createTable() {
            return new ModernTable();
        }

        @Override
        public Sofa createSofa() {
            return new ModernSofa();
        }
    }

    public static class VictorianFurnitureFactory implements FurnitureFactory {
        @Override
        public Chair createChair() {
            return new VictorianChair();
        }

        @Override
        public Table createTable() {
            return new VictorianTable();
        }

        @Override
        public Sofa createSofa() {
            return new VictorianSofa();
        }
    }

    // ==========================================
    // 6. CLIENTE (Sistema de Decoración)
    // ==========================================
    public static class DecorationSystem {
        private final Chair chair;
        private final Table table;
        private final Sofa sofa;

        // El cliente recibe una fábrica y obtiene los muebles garantizando que combinan
        public DecorationSystem(FurnitureFactory factory) {
            this.chair = factory.createChair();
            this.table = factory.createTable();
            this.sofa = factory.createSofa();
        }

        // El cliente interactúa con los muebles sin saber de qué estilo son exactamente
        public void testFurniture() {
            System.out.println("--- Probando el mobiliario de la habitación ---");
            chair.sitOn();
            table.use();
            sofa.lieOn();
            System.out.println("--- Prueba finalizada ---\n");
        }
    }

    // ==========================================
    // 7. DEMOSTRACIÓN (MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // Cliente solicita decorar una sala estilo Moderno
        System.out.println(">>> Decorando sala principal (Estilo Moderno)");
        FurnitureFactory modernFactory = new ModernFurnitureFactory();
        DecorationSystem modernRoom = new DecorationSystem(modernFactory);
        modernRoom.testFurniture();

        // Cliente solicita decorar una biblioteca estilo Victoriano
        System.out.println(">>> Decorando biblioteca (Estilo Victoriano)");
        FurnitureFactory victorianFactory = new VictorianFurnitureFactory();
        DecorationSystem victorianRoom = new DecorationSystem(victorianFactory);
        victorianRoom.testFurniture();
    }
}
