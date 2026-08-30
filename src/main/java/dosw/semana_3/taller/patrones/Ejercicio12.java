package dosw.semana_3.taller.patrones;

/**
 * -------------------------------------------------------------------
 * PARTE III — IDENTIFICANDO PATRONES DE DISEÑO
 * #12 Múltiples Formas de Pago
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * El flujo de compra es idéntico, pero el algoritmo final de cobro varía 
 * dinámicamente según si el cliente elige Tarjeta, PSE, PayPal o Nequi.
 *
 * (2) CATEGORÍA:
 * Patrón de Comportamiento (Behavioral Pattern).
 *
 * (3) PATRÓN SELECCIONADO:
 * Strategy (Estrategia).
 *
 * (4) ¿POR QUÉ?:
 * El patrón Strategy permite definir una familia de algoritmos (las diferentes 
 * pasarelas de pago), encapsular cada uno de ellos en una clase separada y 
 * hacerlos intercambiables en tiempo de ejecución. La clase de contexto 
 * (el carrito de compras) delega el trabajo al objeto Strategy en lugar de 
 * implementar múltiples `if-else` o un gran bloque `switch`.
 *
 * (5) ¿APLICA MÁS DE UN PATRÓN? (Comparación):
 * Podría pensarse en el patrón **Template Method** (Método Plantilla).
 * ¿Por qué Strategy es MEJOR en este caso?
 * - Template Method se basa en **herencia**: Define un esqueleto de algoritmo 
 *   en una clase base y deja que las subclases reescriban ciertos pasos. Esto 
 *   significaría tener un `CompraConTarjeta`, `CompraConNequi`, etc.
 * - Strategy se basa en **composición**: El algoritmo entero se abstrae en 
 *   otra clase que se inyecta. Esto es mucho más flexible, permite cambiar 
 *   la estrategia en caliente (en tiempo de ejecución) y favorece el 
 *   principio OCP (visto en el Ejercicio 6, donde usamos precisamente Strategy).
 */
public class Ejercicio12 {

    // --- IMPLEMENTACIÓN DEL PATRÓN STRATEGY ---

    // 1. La Interfaz Strategy (El algoritmo encapsulado)
    public interface PaymentStrategy {
        void pay(double amount);
    }

    // 2. Estrategias Concretas (Familia de algoritmos)
    
    public static class CreditCardStrategy implements PaymentStrategy {
        private String cardNumber;
        public CreditCardStrategy(String cardNumber) { this.cardNumber = cardNumber; }

        @Override
        public void pay(double amount) {
            System.out.println("Pagando $" + amount + " usando Tarjeta de Crédito terminada en " + cardNumber.substring(cardNumber.length() - 4));
        }
    }

    public static class PayPalStrategy implements PaymentStrategy {
        private String email;
        public PayPalStrategy(String email) { this.email = email; }

        @Override
        public void pay(double amount) {
            System.out.println("Pagando $" + amount + " usando cuenta PayPal de " + email);
        }
    }

    public static class NequiStrategy implements PaymentStrategy {
        private String phone;
        public NequiStrategy(String phone) { this.phone = phone; }

        @Override
        public void pay(double amount) {
            System.out.println("Pagando $" + amount + " haciendo push a Nequi al número " + phone);
        }
    }

    // 3. El Contexto (El flujo principal que permanece inalterable)
    public static class ShoppingCart {
        
        public void checkout(double totalAmount, PaymentStrategy paymentMethod) {
            System.out.println("--- Iniciando flujo de compra ---");
            System.out.println("Verificando stock...");
            System.out.println("Calculando envíos...");
            
            // Delegación del algoritmo específico (¡Aquí entra la Estrategia!)
            paymentMethod.pay(totalAmount);
            
            System.out.println("Enviando correo de confirmación...");
            System.out.println("--- Compra finalizada ---\n");
        }
    }

    // --- DEMOSTRACIÓN ---
    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        
        // Cliente 1: Compra con Tarjeta
        PaymentStrategy cardPayment = new CreditCardStrategy("1234567890123456");
        cart.checkout(150.0, cardPayment);

        // Cliente 2: Compra con Nequi
        PaymentStrategy nequiPayment = new NequiStrategy("3001234567");
        cart.checkout(200.0, nequiPayment);
    }
}
