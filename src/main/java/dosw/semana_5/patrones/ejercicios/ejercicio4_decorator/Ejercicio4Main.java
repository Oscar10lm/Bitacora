package dosw.semana_5.patrones.ejercicios.ejercicio4_decorator;

import java.util.Scanner;

public class Ejercicio4Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Bebida miCafe = new CafeBase();
        
        System.out.println("☕ BIENVENIDO A LA CAFETERÍA DECORATOR ☕");
        System.out.println("Tienes un: " + miCafe.getDescripcion() + " ($" + miCafe.getCosto() + ")");
        
        System.out.println("¿Añadir Leche por $1000? (si/no): ");
        if (scanner.nextLine().equalsIgnoreCase("si")) {
            miCafe = new LecheDecorator(miCafe);
        }
        
        System.out.println("¿Añadir Caramelo por $1500? (si/no): ");
        if (scanner.nextLine().equalsIgnoreCase("si")) {
            miCafe = new CarameloDecorator(miCafe);
        }
        
        System.out.println("\n✅ Cuenta Final:");
        System.out.println("Bebida: " + miCafe.getDescripcion());
        System.out.println("Total a pagar: $" + miCafe.getCosto());
        
        scanner.close();
    }
}
