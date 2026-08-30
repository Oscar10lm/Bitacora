package dosw.semana_4.taller.ejercicio1;

public class TarjetaStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("Procesando pago con Tarjeta de Crédito por la cantidad de: $" + amount);
    }
}
