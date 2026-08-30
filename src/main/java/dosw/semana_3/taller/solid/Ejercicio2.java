package dosw.semana_3.taller.solid;

/**
 * -------------------------------------------------------------------
 * #2 La Aplicación de Transporte
 * -------------------------------------------------------------------
 *
 * (2) PROBLEMA PRINCIPAL:
 * La clase hija (Boat) no puede cumplir con el contrato establecido por la
 * clase padre (Vehicle), por lo que opta por lanzar una excepción.
 *
 * (3) PRINCIPIO SOLID VIOLADO:
 * LSP - Liskov Substitution Principle (Principio de Sustitución de Liskov).
 * "Los objetos de una clase derivada deben poder sustituir a los objetos
 * de la clase base sin alterar el correcto funcionamiento del programa."
 *
 * (4) JUSTIFICACIÓN TÉCNICA:
 * El código cliente que utiliza la clase base `Vehicle` espera que todos los
 * vehículos puedan ejecutar el método `move()` sin problemas. Al pasar una
 * instancia de `Boat`, el programa lanzará una `UnsupportedOperationException`,
 * provocando fallos en tiempo de ejecución. Esto demuestra que `Boat` no es 
 * verdaderamente sustituible por `Vehicle` bajo el diseño actual, rompiendo 
 * el polimorfismo.
 *
 * (5) SOLUCIÓN PROPUESTA (Refactorización):
 * En lugar de forzar una única jerarquía con un método que no aplica a todos,
 * debemos utilizar interfaces segregadas según las capacidades reales del
 * medio de transporte (e.g., conducir, navegar, volar).
 */
public class Ejercicio2 {

    // --- SOLUCIÓN REFACTORIZADA ---

    /**
     * Interfaces específicas basadas en capacidades reales.
     * Esto también apoya el Interface Segregation Principle (ISP).
     */
    public interface Drivable {
        void drive();
    }

    public interface Sailable {
        void sail();
    }

    public interface Flyable {
        void fly();
    }

    // Los vehículos implementan solo los comportamientos que realmente soportan.

    public static class Car implements Drivable {
        @Override
        public void drive() {
            System.out.println("El carro se mueve por tierra.");
        }
    }

    public static class Bicycle implements Drivable {
        @Override
        public void drive() {
            System.out.println("La bicicleta se mueve por tierra.");
        }
    }

    public static class Airplane implements Flyable, Drivable {
        @Override
        public void fly() {
            System.out.println("El avión vuela por el aire.");
        }

        @Override
        public void drive() {
            System.out.println("El avión rueda por la pista.");
        }
    }

    public static class Boat implements Sailable {
        @Override
        public void sail() {
            System.out.println("El bote navega por el agua.");
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        Drivable myCar = new Car();
        myCar.drive(); // OK

        Sailable myBoat = new Boat();
        myBoat.sail(); // OK, sin excepciones
        
        // Ya no podemos hacer: Vehicle v = new Boat(); v.move(); 
        // El diseño evita el error en tiempo de compilación.
    }
}
