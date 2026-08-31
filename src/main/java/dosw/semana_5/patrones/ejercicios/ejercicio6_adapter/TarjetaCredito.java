package dosw.semana_5.patrones.ejercicios.ejercicio6_adapter;

public class TarjetaCredito implements PagoTarjeta {
    @Override
    public void pagarConTarjeta(String numeroTarjeta, double monto) {
        System.out.println("💳 Procesando pago de $" + monto + " con la Tarjeta " + numeroTarjeta);
    }
}
