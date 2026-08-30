package dosw.semana_3.taller.patrones;

/**
 * -------------------------------------------------------------------
 * PARTE III — IDENTIFICANDO PATRONES DE DISEÑO
 * #17 Construcción de Vehículos Configurables
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * Tenemos vehículos con múltiples parámetros de configuración (motor, 
 * transmisión, color, GPS, sonido, etc.), donde muchos son opcionales. 
 * El constructor clásico requeriría 15 argumentos, lo cual es inmanejable 
 * y propenso a errores (anti-patrón "Telescoping Constructor").
 *
 * (2) CATEGORÍA:
 * Patrón Creacional (Creational Pattern).
 *
 * (3) PATRÓN SELECCIONADO:
 * Builder (Constructor).
 *
 * (4) ¿POR QUÉ?:
 * El patrón Builder está diseñado específicamente para aislar la construcción 
 * de un objeto complejo de su representación. En lugar de pasar decenas de 
 * parámetros a un constructor, usamos un objeto intermedio (el Builder) 
 * que nos permite configurar el vehículo paso a paso usando métodos claros 
 * y legibles (ej. `.setEngine()`, `.addGps()`). Solo invocamos el método 
 * final `.build()` cuando hemos terminado de configurarlo.
 *
 * (5) ¿APLICA MÁS DE UN PATRÓN? (Comparación):
 * - Podría evaluarse el uso de **Factory Method**. Sin embargo, la fábrica 
 *   es ideal cuando la creación del objeto es de "un solo paso" (entregas el 
 *   parámetro y te devuelve el objeto listo). Cuando el objeto requiere 
 *   mucha configuración opcional paso a paso, la fábrica sufriría del mismo 
 *   problema de los 15 parámetros, por lo que **Builder** es abrumadoramente 
 *   superior en este caso.
 */
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
