package dosw.semana_5.patrones.ejercicios.ejercicio8_combo2;

import java.util.Scanner;
import java.util.List;

public class Ejercicio8Main {
    public static void main(String[] args) {
        // Construyendo el Composite
        Categoria menuPrincipal = new Categoria("Menú del Restaurante");
        
        Categoria desayunos = new Categoria("Desayunos");
        desayunos.agregar(new Plato("Huevos Revueltos", 8000));
        desayunos.agregar(new Plato("Panqueques", 12000));
        
        Categoria almuerzos = new Categoria("Almuerzos");
        almuerzos.agregar(new Plato("Bandeja Paisa", 25000));
        
        Categoria postres = new Categoria("Postres (Submenú de Almuerzos)");
        postres.agregar(new Plato("Flan", 5000));
        postres.agregar(new Plato("Helado", 4500));
        almuerzos.agregar(postres);
        
        menuPrincipal.agregar(desayunos);
        menuPrincipal.agregar(almuerzos);
        
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== SISTEMA DE MENÚ (Composite + Iterator) ===");
        System.out.println("1. Ver árbol completo (Impresión Composite)");
        System.out.println("2. Recorrer linealmente con Iterator");
        System.out.print("Elija una opción: ");
        int opcion = scanner.nextInt();
        
        System.out.println("\nResultados:");
        if (opcion == 1) {
            menuPrincipal.imprimir(0);
        } else if (opcion == 2) {
            MenuIterator iterador = new MenuIterator(List.of((MenuComponente)menuPrincipal).iterator());
            while (iterador.hasNext()) {
                MenuComponente c = iterador.next();
                if (c instanceof Categoria) {
                    System.out.println("Categoría: " + c.getNombre());
                } else {
                    System.out.println(" - Plato: " + c.getNombre());
                }
            }
        } else {
            System.out.println("Opción no válida.");
        }
        
        scanner.close();
    }
}
