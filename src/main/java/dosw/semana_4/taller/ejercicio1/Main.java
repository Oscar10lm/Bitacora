package dosw.semana_4.taller.ejercicio1;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Plataforma de Pagos Inteligentes ---");
        
        Checkout checkout = new Checkout();
        
        // Simulación de usuario en Colombia
        System.out.println("\nUsuario de Colombia comprando por $150000:");
        PaymentFactory colombiaFactory = new ColombiaPaymentFactory();
        PaymentStrategy pagoNequi = colombiaFactory.create("Nequi");
        
        checkout.setPaymentStrategy(pagoNequi);
        checkout.processPayment(150000);
        
        // Simulación de usuario en USA
        System.out.println("\nUsuario de USA comprando por $50:");
        PaymentFactory usaFactory = new UsaPaymentFactory();
        PaymentStrategy pagoPayPal = usaFactory.create("PayPal");
        
        checkout.setPaymentStrategy(pagoPayPal);
        checkout.processPayment(50);
    }
}
