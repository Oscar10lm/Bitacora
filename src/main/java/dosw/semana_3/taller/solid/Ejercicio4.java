package dosw.semana_3.taller.solid;

/**
 * -------------------------------------------------------------------
 * #4 El Cajero Inteligente
 * -------------------------------------------------------------------
 *
 * (2) PROBLEMA PRINCIPAL:
 * La interfaz SmartATM agrupa múltiples operaciones distintas (retiros, 
 * depósitos, validación biométrica, transferencias cripto). Los cajeros 
 * más simples se ven obligados a implementar métodos que no soportan,
 * generando código inútil o lanzando excepciones inesperadas.
 *
 * (3) PRINCIPIO SOLID VIOLADO:
 * ISP - Interface Segregation Principle (Principio de Segregación de Interfaces).
 * "Ningún cliente debe ser forzado a depender de métodos que no utiliza."
 *
 * (4) JUSTIFICACIÓN TÉCNICA:
 * Una interfaz "gorda" o inflada causa acoplamiento innecesario. Si el día 
 * de mañana la firma del método `cryptocurrencyTransfer` cambia, el cajero 
 * básico (que ni siquiera lo usa) tendrá que ser modificado y recompilado. 
 * Además, esto engaña al código cliente, haciéndole creer que un cajero 
 * básico puede hacer cosas que en realidad fallarán en tiempo de ejecución.
 *
 * (5) SOLUCIÓN PROPUESTA (Refactorización):
 * Dividir la gran interfaz SmartATM en interfaces más pequeñas, cohesivas y 
 * específicas (segregación). Cada cajero implementará únicamente las 
 * interfaces que correspondan a sus capacidades reales.
 */
public class Ejercicio4 {

    // --- SOLUCIÓN REFACTORIZADA ---

    /**
     * Segregamos las responsabilidades en interfaces pequeñas y enfocadas.
     */
    public interface Withdrawable {
        void withdraw();
    }

    public interface Depositable {
        void deposit();
    }

    public interface StatementPrinter {
        void printStatement();
    }

    public interface BiometricValidator {
        void biometricValidation();
    }

    public interface CryptoTransferable {
        void cryptocurrencyTransfer();
    }

    // --- IMPLEMENTACIONES ---

    /**
     * El cajero básico solo implementa lo que realmente sabe hacer.
     * Ya no está forzado a tener métodos vacíos o lanzar excepciones.
     */
    public static class BasicATM implements Withdrawable {
        @Override
        public void withdraw() {
            System.out.println("Cajero Básico: Retirando dinero...");
        }
    }

    /**
     * Un cajero avanzado puede implementar múltiples interfaces 
     * según sus características.
     */
    public static class AdvancedATM implements Withdrawable, Depositable, StatementPrinter, BiometricValidator {
        @Override
        public void withdraw() {
            System.out.println("Cajero Avanzado: Retirando dinero...");
        }

        @Override
        public void deposit() {
            System.out.println("Cajero Avanzado: Recibiendo depósito...");
        }

        @Override
        public void printStatement() {
            System.out.println("Cajero Avanzado: Imprimiendo extracto...");
        }

        @Override
        public void biometricValidation() {
            System.out.println("Cajero Avanzado: Validando huella dactilar...");
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        // Uso de un cajero básico
        Withdrawable basicAtm = new BasicATM();
        basicAtm.withdraw();

        // Uso de un cajero avanzado (se le puede tratar según la necesidad)
        AdvancedATM advancedAtm = new AdvancedATM();
        advancedAtm.withdraw();
        advancedAtm.deposit();
        advancedAtm.biometricValidation();
    }
}

/*
 * --- PREGUNTAS GUÍA ---
 * 
 * 1. ¿Cuántas interfaces más cohesivas propondría en lugar de SmartATM? Nómbrelas y asígneles sus métodos.
 * R/ Tal como se implementó en la solución, propongo 5 interfaces:
 *    - Withdrawable: void withdraw();
 *    - Depositable: void deposit();
 *    - StatementPrinter: void printStatement();
 *    - BiometricValidator: void biometricValidation();
 *    - CryptoTransferable: void cryptocurrencyTransfer();
 * 
 * 2. ¿Qué problemas concretos genera que BasicATM tenga que implementar cryptocurrencyTransfer()?
 * R/ Genera un acoplamiento innecesario. Obliga a la clase BasicATM a incluir un método que 
 * nunca usará, forzando a dejarlo vacío o lanzar una excepción. Además, si la firma del método
 * para transferencias cripto cambia en un futuro, BasicATM tendrá que modificarse y recompilarse
 * a pesar de no tener ninguna relación real con esa funcionalidad.
 * 
 * 3. Si se agrega un cajero multidivisa, ¿qué interfaz(ces) debería implementar? ¿Tendría que tocar BasicATM?
 * R/ Debería implementar las interfaces básicas como `Withdrawable` y quizás una interfaz nueva 
 * llamada `CurrencyExchangeable`. ¡NO tendríamos que tocar BasicATM en absoluto! BasicATM seguirá
 * dependiendo únicamente de su interfaz pequeña `Withdrawable`.
 * 
 * 4. ¿Cómo el ISP facilita agregar nuevas funcionalidades sin romper implementaciones existentes?
 * R/ Al tener interfaces pequeñas ("segregadas"), agregar una nueva funcionalidad implica crear 
 * una nueva interfaz o implementar una adicional en la clase que lo requiera. Las clases 
 * existentes que no necesitan esa funcionalidad nueva no se ven obligadas a implementarla, 
 * manteniendo su código intacto y libre de errores secundarios.
 */
