package dosw.semana_3.taller.patrones;

public class Ejercicio17 {

    // --- IMPLEMENTACIÓN DEL PATRÓN BUILDER ---

    // 1. El objeto complejo a construir
    public static class Vehicle {
        private String type; // Carro, moto, camión
        private String engine;
        private String transmission;
        private String color;
        private boolean hasGps;
        private boolean hasPremiumSound;

        // Constructor privado: solo el Builder puede instanciar el vehículo
        private Vehicle(VehicleBuilder builder) {
            this.type = builder.type;
            this.engine = builder.engine;
            this.transmission = builder.transmission;
            this.color = builder.color;
            this.hasGps = builder.hasGps;
            this.hasPremiumSound = builder.hasPremiumSound;
        }

        @Override
        public String toString() {
            return "Vehicle [Type=" + type + ", Engine=" + engine + 
                   ", Color=" + color + ", GPS=" + (hasGps ? "Yes" : "No") + "]";
        }
    }

    // 2. El Builder (usualmente una clase estática interna)
    public static class VehicleBuilder {
        // Atributos obligatorios
        private String type;
        
        // Atributos opcionales (con valores por defecto)
        private String engine = "Standard Engine";
        private String transmission = "Manual";
        private String color = "White";
        private boolean hasGps = false;
        private boolean hasPremiumSound = false;

        // El constructor del Builder solo pide lo estrictamente necesario
        public VehicleBuilder(String type) {
            this.type = type;
        }

        // Métodos de configuración (Fluent Interface) que retornan el mismo Builder
        public VehicleBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public VehicleBuilder setColor(String color) {
            this.color = color;
            return this;
        }

        public VehicleBuilder addGps() {
            this.hasGps = true;
            return this;
        }

        public VehicleBuilder addPremiumSound() {
            this.hasPremiumSound = true;
            return this;
        }

        // 3. El método final que ensambla todo
        public Vehicle build() {
            return new Vehicle(this);
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        
        // Creamos un carro básico (solo definimos su tipo)
        Vehicle basicCar = new VehicleBuilder("Carro Básico")
                                .build();
                                
        System.out.println(basicCar);

        // Creamos una camioneta de lujo configurando paso a paso (¡Adiós 15 parámetros!)
        Vehicle luxuryTruck = new VehicleBuilder("Camioneta")
                                .setEngine("V8 Turbo")
                                .setColor("Negro Mate")
                                .addGps()
                                .addPremiumSound()
                                .build();
                                
        System.out.println(luxuryTruck);
        
        // El código es inmensamente más legible y no nos equivocaremos de orden.
    }
}
