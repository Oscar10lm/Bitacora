package dosw.semana_5.patrones.ejercicios.ejercicio6_adapter;

import java.util.Scanner;

public class Ejercicio6Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("💰 SISTEMA DE PAGOS 💰");
        System.out.println("Total a pagar: $100.0");
        System.out.println("Seleccione su método de pago:");
        System.out.println("1. Tarjeta de Crédito | 2. Bitcoin (Crypto)");
        System.out.print("Opción: ");
        
        int opcion = scanner.nextInt();
        scanner.nextLine();
        
        System.out.print("Ingrese número de cuenta/tarjeta: ");
        String numero = scanner.nextLine();
        
        PagoTarjeta procesador;
        
        if (opcion == 1) {
            procesador = new TarjetaCredito();
        } else {
            // Aquí entra la magia del Adapter
            CryptoAPI apiExterna = new CryptoAPI();
            procesador = new CryptoAdapter(apiExterna);
        }
        
        // Nuestro sistema siempre llama a "pagarConTarjeta", no le importa si es Crypto
        procesador.pagarConTarjeta(numero, 100.0);
        
        scanner.close();
    }
}
