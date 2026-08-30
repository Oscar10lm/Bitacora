package dosw.semana_3.extra.patrones.creacionales.factory_method;

/**
 * ============================================================================
 * PATRÓN DE DISEÑO: FACTORY METHOD (CREACIONAL)
 * ============================================================================
 *
 * DEFINICIÓN:
 * Es un patrón de diseño creacional que proporciona una interfaz para crear 
 * objetos en una superclase, mientras permite a las subclases alterar el tipo 
 * de objetos que se crearán.
 *
 * VENTAJAS CLAVE:
 * - Evita un acoplamiento fuerte entre el creador (Processor) y los productos 
 *   concretos (Payments).
 * - SINGLE RESPONSIBILITY PRINCIPLE (SRP): Se extrae el código de creación del 
 *   producto hacia un lugar específico, haciendo el programa más fácil de mantener.
 * - OPEN/CLOSED PRINCIPLE (OCP): Permite incorporar nuevos tipos de productos 
 *   en el programa sin descomponer o alterar el código cliente existente.
 *
 * ----------------------------------------------------------------------------
 * EJERCICIO PRÁCTICO: PROCESADOR DE PAGOS
 * ----------------------------------------------------------------------------
 * Una plataforma de comercio electrónico procesa pagos usando distintos métodos:
 * Tarjeta de crédito, PayPal, Transferencia bancaria.
 * El sistema no se acopla a las clases concretas de cada pago.
 */
public class PaymentSystem {

    // ==========================================
    // 1. PRODUCTO (La interfaz común)
    // ==========================================
    public interface Payment {
        void pay(double amount);
    }

    // ==========================================
    // 2. PRODUCTOS CONCRETOS (Implementaciones)
    // ==========================================
    public static class CreditCardPayment implements Payment {
        @Override
        public void pay(double amount) {
            System.out.printf("Pago con TARJETA DE CRÉDITO por $%.2f\n", amount);
        }
    }

    public static class PayPalPayment implements Payment {
        @Override
        public void pay(double amount) {
            System.out.printf("Pago con PAYPAL por $%.2f\n", amount);
        }
    }

    public static class BankTransferPayment implements Payment {
        @Override
        public void pay(double amount) {
            System.out.printf("Pago con TRANSFERENCIA BANCARIA por $%.2f\n", amount);
        }
    }

    // ==========================================
    // 3. CREADOR (Clase Abstracta / Superclase)
    // ==========================================
    public static abstract class PaymentProcessor {
        
        // El "Factory Method" puro: Las subclases deciden qué instanciar
        protected abstract Payment createPayment();

        // Lógica de negocio principal que confía en el Factory Method
        public void processPayment(double amount) {
            // Se invoca al Factory Method para obtener un objeto producto
            Payment payment = createPayment();
            
            System.out.println("Iniciando validaciones de seguridad del procesador...");
            // Se usa el producto sin saber qué clase concreta es realmente
            payment.pay(amount);
            System.out.println("Procesamiento completado exitosamente.\n");
        }
    }

    // ==========================================
    // 4. CREADORES CONCRETOS (Subclases)
    // ==========================================
    public static class CreditCardProcessor extends PaymentProcessor {
        @Override
        protected Payment createPayment() {
            return new CreditCardPayment();
        }
    }

    public static class PayPalProcessor extends PaymentProcessor {
        @Override
        protected Payment createPayment() {
            return new PayPalPayment();
        }
    }

    public static class BankTransferProcessor extends PaymentProcessor {
        @Override
        protected Payment createPayment() {
            return new BankTransferPayment();
        }
    }

    // ==========================================
    // 5. CLIENTE (Demostración / MainClass)
    // ==========================================
    public static void main(String[] args) {
        
        // El cliente decide qué procesador usar (usualmente basado en la UI o configuración)
        
        System.out.println("--- Transacción 1 ---");
        PaymentProcessor processor1 = new CreditCardProcessor();
        processor1.processPayment(150.75);

        System.out.println("--- Transacción 2 ---");
        PaymentProcessor processor2 = new PayPalProcessor();
        processor2.processPayment(89.99);

        System.out.println("--- Transacción 3 ---");
        PaymentProcessor processor3 = new BankTransferProcessor();
        processor3.processPayment(1250.00);
        
        /* 
         * ¿Por qué cumple OCP?
         * Si mañana necesitamos pagar con Crypto, solo creamos 'CryptoPayment' 
         * y 'CryptoProcessor'. La clase base 'PaymentProcessor' y el resto de 
         * procesadores NO se tocan.
         */
    }
}
