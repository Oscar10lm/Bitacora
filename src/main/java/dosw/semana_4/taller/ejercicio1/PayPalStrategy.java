package dosw.semana_4.taller.ejercicio1;

public class PayPalStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("Procesando pago con PayPal por la cantidad de: $" + amount);
    }
}
