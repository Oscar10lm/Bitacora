package dosw.semana_4.taller.ejercicio1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Plataforma de Pagos Inteligentes ---");

        Scanner sc = new Scanner(System.in);
        System.out.println("Digite su nacionalidad ");
        String nacionalidad = sc.nextLine();

        System.out.println("Digite su precio ");
        double precio = sc.nextDouble();

        System.out.println("Digite su medio de pago");
        String medio = sc.nextLine();

        Checkout checkout = new Checkout();

        if(nacionalidad.equalsIgnoreCase("colombia")){
            System.out.println("\nUsuario de Colombia comprando por $150000:");
            PaymentFactory colombiaFactory = new ColombiaPaymentFactory();
            PaymentStrategy pagoNequi = colombiaFactory.create(medio);
            checkout.setPaymentStrategy(pagoNequi);
            checkout.processPayment(precio);
        }

        
        // Simulación de usuario en USA
        System.out.println("\nUsuario de USA comprando por $50:");
        PaymentFactory usaFactory = new UsaPaymentFactory();
        PaymentStrategy pagoPayPal = usaFactory.create("PayPal");
        
        checkout.setPaymentStrategy(pagoPayPal);
        checkout.processPayment(50);
    }
}
