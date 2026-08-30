package dosw.semana_4.taller.ejercicio1;

public class ColombiaPaymentFactory implements PaymentFactory {
    @Override
    public PaymentStrategy create(String type) {
        if ("PSE".equalsIgnoreCase(type)) {
            return new PseStrategy();
        } else if ("Nequi".equalsIgnoreCase(type)) {
            return new NequiStrategy();
        } else if ("Tarjeta".equalsIgnoreCase(type)) {
            return new TarjetaStrategy();
        }
        throw new IllegalArgumentException("Tipo de pago no soportado en Colombia: " + type);
    }
}
