package dosw.semana_5.patrones.ejercicios.ejercicio13_combo7;

import java.util.Scanner;

public class Ejercicio13Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BaseDeDatosLegacy bdVieja = new BaseDeDatosLegacy();
        
        System.out.println("=== MIGRACIÓN DE BD (Iterator + Adapter) ===");
        System.out.println("Tenemos una BD Legacy que retorna Strings ilegibles.");
        System.out.print("Presione ENTER para usar el Adapter y extraer los objetos limpios...");
        scanner.nextLine();
        
        BDAdapter adaptador = new BDAdapter(bdVieja);
        
        System.out.println("\nResultados Mapeados:");
        // Gracias a implementar Iterable, podemos usar foreach que por debajo usa el Iterator!
        for (UsuarioModerno usuario : adaptador) {
            System.out.println(" -> " + usuario.toString());
        }
        
        scanner.close();
    }
}
