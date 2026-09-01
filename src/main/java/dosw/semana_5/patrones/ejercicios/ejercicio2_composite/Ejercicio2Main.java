package dosw.semana_5.patrones.ejercicios.ejercicio2_composite;

import java.util.Scanner;

public class Ejercicio2Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Carpeta raiz = new Carpeta("C:");
        
        System.out.println("💻 SISTEMA DE ARCHIVOS 💻");
        
        while (true) {
            System.out.println("\n1. Crear archivo en Raíz | 2. Ver Estructura y Tamaño | 3. Salir");
            System.out.print("Elige: ");
            int opcion = scanner.nextInt();
            scanner.nextLine(); // buffer
            
            if (opcion == 1) {
                System.out.print("Nombre del archivo: ");
                String nombre = scanner.nextLine();
                System.out.print("Tamaño en MB: ");
                int tamano = scanner.nextInt();
                raiz.agregar(new Archivo(nombre, tamano));
                System.out.println("✅ Archivo agregado.");
            } else if (opcion == 2) {
                System.out.println("\n--- Árbol de Directorios ---");
                raiz.mostrarEstructura("");
                System.out.println("Tamaño total de la raíz: " + raiz.getTamano() + " MB");
            } else if (opcion == 3) {
                break;
            }
        }
        scanner.close();
    }
}
