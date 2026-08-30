package dosw.semana_4.taller.ejercicio1;

public class StripeStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("Procesando pago con Stripe por la cantidad de: $" + amount);
    }
}
