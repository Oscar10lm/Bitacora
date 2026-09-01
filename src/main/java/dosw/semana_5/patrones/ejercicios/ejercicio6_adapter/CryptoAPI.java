package dosw.semana_5.patrones.ejercicios.ejercicio6_adapter;

// Esta es una API externa que NO podemos modificar
public class CryptoAPI {
    public void processCryptoPayment(String walletAddress, double usdMonto) {
        System.out.println("🪙 (API Externa) Procesando pago Crypto. Billetera: " + walletAddress + " | Monto USD: " + usdMonto);
        System.out.println("🪙 Blockchain confirmada.");
    }
}
