package dosw.semana_5.patrones.ejercicios.ejercicio11_combo5;

import java.util.Scanner;

public class Ejercicio11Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== FÁBRICA DE AUTOS EUROPEOS (Builder + Adapter) ===");
        System.out.println("Construyendo Auto Europeo...");
        
        System.out.print("Ingrese modelo del auto: ");
        String modelo = scanner.nextLine();
        
        System.out.print("Ingrese kilometraje (KM): ");
        double km = scanner.nextDouble();
        
        System.out.print("Ingrese capacidad de tanque (Litros): ");
        double litros = scanner.nextDouble();
        
        AutoEuropeo autoUE = new AutoEuropeoBuilder()
            .setModelo(modelo)
            .setKm(km)
            .setLitros(litros)
            .build();
            
        System.out.println("\n1. Auto configurado localmente:");
        autoUE.mostrarInfo();
        
        System.out.print("\n¿Desea exportar este auto a Estados Unidos? (1. Sí, 2. No): ");
        if (scanner.nextInt() == 1) {
            System.out.println("\n2. Exportando auto (Aplicando Adapter)...");
            AutoUSA autoExportado = new AutoUSAAdapter(autoUE);
            autoExportado.mostrarInfoUSA();
        } else {
            System.out.println("Exportación cancelada.");
        }
        
        scanner.close();
    }
}
