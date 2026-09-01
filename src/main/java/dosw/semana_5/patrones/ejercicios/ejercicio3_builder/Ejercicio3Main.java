package dosw.semana_5.patrones.ejercicios.ejercicio3_builder;

import java.util.Scanner;

public class Ejercicio3Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PizzaBuilder builder = new PizzaBuilder();
        
        System.out.println("🍕 BIENVENIDO A LA PIZZERÍA BUILDER 🍕");
        
        System.out.println("¿Qué masa desea? (delgada/gruesa): ");
        String masa = scanner.nextLine();
        builder.construirMasa(masa);
        
        System.out.println("¿Qué salsa desea? (BBQ/Tomate): ");
        String salsa = scanner.nextLine();
        builder.construirSalsa(salsa);
        
        System.out.println("¿Desea pepperoni? (si/no): ");
        boolean pepperoni = scanner.nextLine().equalsIgnoreCase("si");
        builder.construirPepperoni(pepperoni);
        
        Pizza miPizza = builder.build();
        System.out.println("\n✅ Su pedido está listo:");
        System.out.println(miPizza.toString());
        
        scanner.close();
    }
}
