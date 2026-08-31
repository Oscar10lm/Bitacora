package dosw.semana_5.patrones.ejercicios.ejercicio1_iterator;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Ejercicio1Main {
    public static void main(String[] args) {
        List<String> misCanciones = Arrays.asList(
            "Bohemian Rhapsody", "Hotel California", "Stairway to Heaven", "Imagine"
        );
        PlaylistIterator iterator = new PlaylistIterator(misCanciones);
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;

        System.out.println("🎵 REPRODUCTOR DE MÚSICA INICIADO 🎵");
        
        while (opcion != 3) {
            System.out.println("\n¿Qué deseas hacer?");
            System.out.println("1. Siguiente canción | 2. Ver actual | 3. Salir");
            System.out.print("Elige una opción: ");
            
            if (scanner.hasNextInt()) {
                opcion = scanner.nextInt();
                switch (opcion) {
                    case 1:
                        if (iterator.hasNext()) {
                            System.out.println("⏭️ Avanzando a: " + iterator.next());
                        } else {
                            System.out.println("⏹️ No hay más canciones.");
                        }
                        break;
                    case 2:
                        System.out.println("▶️ Sonando ahora: " + iterator.current());
                        break;
                    case 3:
                        System.out.println("Apagando reproductor...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } else {
                scanner.next(); // Limpiar buffer
                System.out.println("Entrada inválida.");
            }
        }
        scanner.close();
    }
}
