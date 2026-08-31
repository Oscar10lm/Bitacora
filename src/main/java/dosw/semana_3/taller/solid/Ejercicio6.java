package dosw.semana_3.taller.solid;

public class Ejercicio6 {

    // --- ESQUELETO DE SOLUCIÓN BASADO EN OCP ---

    // La abstracción que permite cerrar el código a modificaciones
    public interface PaymentMethod {
        boolean processPayment(double amount);
    }

    // Extensión 1 (Clase independiente)
    public static class CreditCardPayment implements PaymentMethod {
        @Override
        public boolean processPayment(double amount) {
            System.out.println("Pagando " + amount + " con Tarjeta de Crédito");
            return true;
        }
    }

    // Extensión 2 (Clase independiente, si mañana llega PSE, se crea otra igual)
    public static class NequiPayment implements PaymentMethod {
        @Override
        public boolean processPayment(double amount) {
            System.out.println("Pagando " + amount + " con Nequi");
            return true;
        }
    }

    // Clase central: Cerrada a modificaciones. ¡No hay que tocarla para agregar pagos!
    public static class CheckoutService {
        public void completePurchase(double totalAmount, PaymentMethod paymentMethod) {
            System.out.println("Iniciando flujo de compra...");
            
            // Se usa la abstracción, no importa qué medio concreto sea
            boolean success = paymentMethod.processPayment(totalAmount);
            
            if (success) {
                System.out.println("Compra completada exitosamente.");
            } else {
                System.out.println("Fallo en el pago.");
            }
        }
    }

    public static void main(String[] args) {
        CheckoutService checkout = new CheckoutService();
        
        // El cliente decide usar Nequi. El CheckoutService lo procesa sin conocer los detalles.
        PaymentMethod miPago = new NequiPayment();
        checkout.completePurchase(50000.0, miPago);
    }
}
