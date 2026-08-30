package dosw.semana_4.taller.ejercicio1;

public class Checkout {
    private PaymentStrategy strategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processPayment(double amount) {
        if (strategy == null) {
            throw new IllegalStateException("Estrategia de pago no ha sido configurada.");
        }
        strategy.process(amount);
    }
}
