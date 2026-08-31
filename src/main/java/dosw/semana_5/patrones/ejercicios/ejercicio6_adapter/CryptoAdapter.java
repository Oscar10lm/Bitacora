package dosw.semana_5.patrones.ejercicios.ejercicio6_adapter;

// Este es el adaptador que conecta la interfaz vieja con la API nueva
public class CryptoAdapter implements PagoTarjeta {
    private CryptoAPI cryptoAPI;

    public CryptoAdapter(CryptoAPI cryptoAPI) {
        this.cryptoAPI = cryptoAPI;
    }

    @Override
    public void pagarConTarjeta(String numeroTarjeta, double monto) {
        System.out.println("🔄 [Adapter] Convirtiendo pago de tarjeta a Crypto...");
        // Fingimos que el 'numeroTarjeta' es la wallet y usamos la API externa.
        cryptoAPI.processCryptoPayment("WALLET-" + numeroTarjeta, monto);
    }
}
