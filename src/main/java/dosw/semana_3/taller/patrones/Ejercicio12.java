package dosw.semana_3.taller.patrones;

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
