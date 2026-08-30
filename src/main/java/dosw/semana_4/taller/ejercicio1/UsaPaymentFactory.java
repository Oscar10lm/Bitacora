package dosw.semana_4.taller.ejercicio1;

public class UsaPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy create(String type) {
        if ("PayPal".equalsIgnoreCase(type)) {
            return new PayPalStrategy();
        } else if ("Stripe".equalsIgnoreCase(type)) {
            return new StripeStrategy();
        } else if ("Tarjeta".equalsIgnoreCase(type)) {
            return new TarjetaStrategy();
        }
        throw new IllegalArgumentException("Tipo de pago no soportado en USA: " + type);
    }
}
