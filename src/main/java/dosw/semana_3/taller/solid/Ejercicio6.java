package dosw.semana_3.taller.solid;

/**
 * -------------------------------------------------------------------
 * #6 E-Commerce con Múltiples Medios de Pago
 * -------------------------------------------------------------------
 *
 * (1) SITUACIÓN:
 * Un E-Commerce debe integrar constantemente nuevos medios de pago (cripto, PSE, 
 * Nequi) y el equipo teme que agregar estas opciones rompa el flujo de compra 
 * principal.
 *
 * (2) PRINCIPIO SOLID A APLICAR:
 * OCP - Open/Closed Principle (Principio de Abierto/Cerrado).
 *
 * (3) JUSTIFICACIÓN TÉCNICA:
 * El principal problema es el miedo a romper código existente al agregar 
 * funcionalidades nuevas. OCP dicta que el sistema (el flujo de compra) debe estar 
 * ABIERTO a la extensión (agregar PSE, Nequi), pero CERRADO a la modificación 
 * (no alterar la clase central `CheckoutService` con nuevos `if-else`).
 * Al aplicar OCP, se elimina el riesgo de romper el flujo actual porque no se 
 * toca el código central que ya funciona, simplemente se agregan nuevas clases 
 * que el sistema central es capaz de usar a través de polimorfismo.
 *
 * (4) SOLUCIÓN PROPUESTA (Estructura):
 * Se crea una interfaz `PaymentMethod`. Cada nuevo método de pago será una 
 * clase separada que implemente esta interfaz. El `CheckoutService` solo 
 * dependerá de la interfaz. (Muy similar a la solución del Ejercicio 3, y 
 * se implementa típicamente usando el patrón de diseño Strategy).
 */
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
