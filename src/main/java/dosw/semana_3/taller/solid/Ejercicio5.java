package dosw.semana_3.taller.solid;

/**
 * -------------------------------------------------------------------
 * #5 El Sistema de Pagos
 * -------------------------------------------------------------------
 *
 * (2) PROBLEMA PRINCIPAL:
 * La clase PaymentProcessor instancia directamente a PaypalGateway dentro
 * de su propio código (acoplamiento fuerte). No hay forma de cambiar de
 * proveedor de pagos (ej. a Wompi o Stripe) sin tener que modificar la 
 * clase PaymentProcessor.
 *
 * (3) PRINCIPIO SOLID VIOLADO:
 * DIP - Dependency Inversion Principle (Principio de Inversión de Dependencias).
 * "Los módulos de alto nivel no deben depender de los módulos de bajo nivel. 
 * Ambos deben depender de abstracciones (interfaces). Además, las abstracciones 
 * no deben depender de los detalles; los detalles deben depender de abstracciones."
 *
 * (4) JUSTIFICACIÓN TÉCNICA:
 * PaymentProcessor (módulo de alto nivel/lógica de negocio) está dependiendo 
 * directamente de PaypalGateway (módulo de bajo nivel/detalle de infraestructura).
 * Esto hace que el sistema sea rígido, difícil de extender y difícil de probar 
 * (no se puede hacer un "mock" del gateway de pago fácilmente para pruebas unitarias).
 *
 * (5) SOLUCIÓN PROPUESTA (Refactorización):
 * Crear una interfaz (abstracción) llamada `PaymentGateway`. Hacer que 
 * PaypalGateway, StripeGateway, etc., implementen esta interfaz. Finalmente, 
 * inyectar la dependencia en `PaymentProcessor` a través de su constructor
 * (Inyección de Dependencias), para que dependa de la interfaz y no de la clase concreta.
 */
public class Ejercicio5 {

    // --- SOLUCIÓN REFACTORIZADA ---

    /**
     * Abstracción que define el contrato.
     * Tanto el alto nivel como el bajo nivel dependen de esto.
     */
    public interface PaymentGateway {
        void pay(double amount);
    }

    // --- Detalles (Módulos de bajo nivel) ---

    public static class PaypalGateway implements PaymentGateway {
        @Override
        public void pay(double amount) {
            System.out.println("Procesando $" + amount + " a través de PayPal.");
        }
    }

    public static class WompiGateway implements PaymentGateway {
        @Override
        public void pay(double amount) {
            System.out.println("Procesando $" + amount + " a través de Wompi.");
        }
    }

    public static class StripeGateway implements PaymentGateway {
        @Override
        public void pay(double amount) {
            System.out.println("Procesando $" + amount + " a través de Stripe.");
        }
    }

    // --- Módulo de alto nivel ---

    /**
     * Ahora PaymentProcessor depende de una abstracción (PaymentGateway).
     * No sabe ni le importa si es PayPal, Wompi o Stripe.
     */
    public static class PaymentProcessor {
        
        // Dependencia basada en interfaz, no en una clase concreta.
        private final PaymentGateway gateway;

        // Inyección de dependencias por constructor
        public PaymentProcessor(PaymentGateway gateway) {
            this.gateway = gateway;
        }

        public void processPayment(double amount) {
            // Delega la acción a la abstracción
            gateway.pay(amount);
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        // En el punto de entrada de la aplicación (o contenedor de DI), 
        // armamos las dependencias.

        // Cliente 1 elige pagar con PayPal
        PaymentGateway paypal = new PaypalGateway();
        PaymentProcessor processor1 = new PaymentProcessor(paypal);
        processor1.processPayment(150.0);

        // Cliente 2 elige pagar con Wompi (¡Reusamos el mismo PaymentProcessor!)
        PaymentGateway wompi = new WompiGateway();
        PaymentProcessor processor2 = new PaymentProcessor(wompi);
        processor2.processPayment(300.0);
    }
}
