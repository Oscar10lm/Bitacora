package dosw.semana_3.ejercicios_diapositivas.patrones.creacionales.builder;

public class ComputerBuilderSystem {

    // ==========================================
    // 1. EL PRODUCTO (El objeto complejo resultante)
    // ==========================================
    public static class Computer {
        private String processor;
        private String ram;
        private String storage;
        private String powerSupply;
        
        // Opcionales
        private String dedicatedGpu;
        private String coolingSystem;
        private boolean hasRgb;

        public void setProcessor(String processor) { this.processor = processor; }
        public void setRam(String ram) { this.ram = ram; }
        public void setStorage(String storage) { this.storage = storage; }
        public void setPowerSupply(String powerSupply) { this.powerSupply = powerSupply; }
        public void setDedicatedGpu(String dedicatedGpu) { this.dedicatedGpu = dedicatedGpu; }
        public void setCoolingSystem(String coolingSystem) { this.coolingSystem = coolingSystem; }
        public void setHasRgb(boolean hasRgb) { this.hasRgb = hasRgb; }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("Computadora Ensamblada:\n")
              .append("  - Procesador: ").append(processor).append("\n")
              .append("  - RAM: ").append(ram).append("\n")
              .append("  - Almacenamiento: ").append(storage).append("\n")
              .append("  - Fuente: ").append(powerSupply).append("\n");
              
            if (dedicatedGpu != null) sb.append("  - GPU Dedicada: ").append(dedicatedGpu).append("\n");
            if (coolingSystem != null) sb.append("  - Refrigeración: ").append(coolingSystem).append("\n");
            sb.append("  - Luces RGB: ").append(hasRgb ? "Sí" : "No").append("\n");
            
            return sb.toString();
        }
    }

    // ==========================================
    // 2. INTERFAZ BUILDER (Pasos comunes de construcción)
    // ==========================================
    public interface ComputerBuilder {
        void reset();
        void buildProcessor();
        void buildRAM();
        void buildStorage();
        void buildPowerSupply();
        void buildOptionals();
        Computer getResult();
    }

    // ==========================================
    // 3. CONSTRUCTORES CONCRETOS
    // ==========================================
    
    // Constructor para la PC Gamer
    public static class GamerPCBuilder implements ComputerBuilder {
        private Computer result;

        public GamerPCBuilder() {
            this.reset();
        }

        @Override
        public void reset() {
            this.result = new Computer();
        }

        @Override
        public void buildProcessor() {
            result.setProcessor("Intel Core i9 14900K");
        }

        @Override
        public void buildRAM() {
            result.setRam("32GB DDR5 6000MHz");
        }

        @Override
        public void buildStorage() {
            result.setStorage("2TB NVMe M.2 Gen4");
        }

        @Override
        public void buildPowerSupply() {
            result.setPowerSupply("1000W 80+ Gold");
        }

        @Override
        public void buildOptionals() {
            result.setDedicatedGpu("NVIDIA RTX 4090 24GB");
            result.setCoolingSystem("Refrigeración Líquida AIO 360mm");
            result.setHasRgb(true);
        }

        @Override
        public Computer getResult() {
            Computer builtComputer = this.result;
            this.reset();
            return builtComputer;
        }
    }

    // Constructor para la PC de Oficina
    public static class OfficePCBuilder implements ComputerBuilder {
        private Computer result;

        public OfficePCBuilder() {
            this.reset();
        }

        @Override
        public void reset() {
            this.result = new Computer();
        }

        @Override
        public void buildProcessor() {
            result.setProcessor("Intel Core i3 12100");
        }

        @Override
        public void buildRAM() {
            result.setRam("8GB DDR4 3200MHz");
        }

        @Override
        public void buildStorage() {
            result.setStorage("500GB SSD SATA");
        }

        @Override
        public void buildPowerSupply() {
            result.setPowerSupply("450W 80+ Bronze");
        }

        @Override
        public void buildOptionals() {
            // La PC de oficina no lleva GPU dedicada ni refrigeración líquida especial
            result.setDedicatedGpu(null); 
            result.setCoolingSystem("Disipador de aire estándar");
            result.setHasRgb(false);
        }

        @Override
        public Computer getResult() {
            Computer builtComputer = this.result;
            this.reset();
            return builtComputer;
        }
    }

    // ==========================================
    // 4. EL DIRECTOR (Define el orden de ejecución)
    // ==========================================
    public static class ComputerDirector {
        private ComputerBuilder builder;

        public void setBuilder(ComputerBuilder builder) {
            this.builder = builder;
        }

        // Construir la PC con todos los componentes posibles (ideal para la Gamer)
        public void constructFullPC() {
            builder.buildProcessor();
            builder.buildRAM();
            builder.buildStorage();
            builder.buildPowerSupply();
            builder.buildOptionals(); // Se instalan los extras
        }

        // Construir una PC estrictamente básica para abaratar costos extremos
        public void constructBasicPC() {
            builder.buildProcessor();
            builder.buildRAM();
            builder.buildStorage();
            builder.buildPowerSupply();
            // Se omite deliberadamente el paso de opcionales
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        ComputerDirector director = new ComputerDirector();

        // 1. Cliente pide una PC Gamer full armada
        System.out.println("--- Pedido #1: Ensamblando PC Gamer ---");
        ComputerBuilder gamerBuilder = new GamerPCBuilder();
        director.setBuilder(gamerBuilder);
        director.constructFullPC();
        Computer gamerPC = gamerBuilder.getResult();
        System.out.println(gamerPC);

        // 2. Cliente pide una PC de Oficina estándar
        System.out.println("--- Pedido #2: Ensamblando PC de Oficina ---");
        ComputerBuilder officeBuilder = new OfficePCBuilder();
        director.setBuilder(officeBuilder);
        director.constructFullPC(); // La oficina sí tiene paso "opcionales" (aire estándar)
        Computer officePC = officeBuilder.getResult();
        System.out.println(officePC);

        // 3. Cliente pide la PC Gamer pero sin lujos (Omitiendo opcionales por presupuesto)
        System.out.println("--- Pedido #3: Ensamblando PC Gamer (Sin GPU dedicada ni extras) ---");
        director.setBuilder(gamerBuilder);
        director.constructBasicPC(); // El Director dicta no hacer el paso final
        Computer budgetGamerPC = gamerBuilder.getResult();
        System.out.println(budgetGamerPC);
    }
}
