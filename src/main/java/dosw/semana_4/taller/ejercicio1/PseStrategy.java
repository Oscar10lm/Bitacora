package dosw.semana_4.taller.ejercicio1;

public class PseStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("Procesando pago con PSE por la cantidad de: $" + amount);
    }
}
