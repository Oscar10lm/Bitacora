package dosw.semana_4.taller.ejercicio1;

public interface PaymentFactory {
    PaymentStrategy create(String type);
}
