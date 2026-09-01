package dosw.semana_5.patrones.ejercicios.ejercicio7_combo1;

import java.util.Scanner;

public class Ejercicio7Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PCBuilder builder = new PCBuilder();
        
        System.out.println("=== CONFIGURADOR DE PC GAMER ===");
        System.out.println("1. Construir núcleo (Builder)");
        
        System.out.print("Elija CPU (1. i5, 2. i7 +$100, 3. i9 +$250): ");
        int cpu = scanner.nextInt();
        if (cpu == 2) builder.setCpu("Intel i7", 100);
        else if (cpu == 3) builder.setCpu("Intel i9", 250);
        else builder.setCpu("Intel i5", 0);
        
        System.out.print("Elija RAM (1. 8GB, 2. 16GB +$50, 3. 32GB +$120): ");
        int ram = scanner.nextInt();
        if (ram == 2) builder.setRam("16GB", 50);
        else if (ram == 3) builder.setRam("32GB", 120);
        else builder.setRam("8GB", 0);
        
        PC miPC = builder.build();
        System.out.println("\nPC Base construida: " + miPC.getDescripcion() + " | Precio: $" + miPC.getPrecio());
        
        System.out.println("\n2. Decorar PC (Decorator)");
        System.out.print("¿Agregar Luces RGB por $50? (1. Sí, 2. No): ");
        if (scanner.nextInt() == 1) {
            miPC = new LucesRGBDecorator(miPC);
        }
        
        System.out.print("¿Agregar Refrigeración Líquida por $150? (1. Sí, 2. No): ");
        if (scanner.nextInt() == 1) {
            miPC = new RefrigeracionDecorator(miPC);
        }
        
        System.out.println("\n=== RESUMEN FINAL ===");
        System.out.println("Componentes: " + miPC.getDescripcion());
        System.out.println("Total a pagar: $" + miPC.getPrecio());
        
        scanner.close();
    }
}
