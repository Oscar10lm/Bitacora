package dosw.semana_4.taller.ejercicio1;

public class NequiStrategy implements PaymentStrategy {
    @Override
    public void process(double amount) {
        System.out.println("Procesando pago con Nequi por la cantidad de: $" + amount);
    }
}
