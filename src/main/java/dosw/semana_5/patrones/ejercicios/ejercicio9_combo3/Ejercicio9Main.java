package dosw.semana_5.patrones.ejercicios.ejercicio9_combo3;

import java.util.Scanner;

public class Ejercicio9Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Configurar Cadena de Responsabilidad (CoR)
        FiltroBancario verificarSaldo = new VerificarSaldo();
        FiltroBancario verificarFraude = new VerificarFraude();
        FiltroBancario procesarPago = new ProcesarPago(); // Este usa el Adapter por dentro
        
        verificarSaldo.setSiguiente(verificarFraude);
        verificarFraude.setSiguiente(procesarPago);
        
        System.out.println("=== PASARELA BANCARIA (CoR + Adapter) ===");
        System.out.print("Ingrese número de cuenta: ");
        String cuenta = scanner.nextLine();
        
        System.out.print("Ingrese monto a transferir: $");
        double monto = scanner.nextDouble();
        
        System.out.print("¿Es una cuenta en lista negra? (1. Sí, 2. No): ");
        boolean fraude = scanner.nextInt() == 1;
        
        System.out.println("\nIniciando Cadena de validaciones...");
        verificarSaldo.procesar(cuenta, monto, fraude);
        
        scanner.close();
    }
}
