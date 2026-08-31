package dosw.semana_1.streams.extra;

import java.util.List;

public class Ejercicio2 {

    /**
     * Filtra palabras de más de 4 letras, las convierte a mayúsculas,
     * las ordena alfabéticamente y cuenta el total de palabras resultantes.
     */

    public static void ejercicio2() {
        List<String> words = List.of("java", "stream", "api", "functional", "code", "git");

        // Opción 1: Separado en dos pasos (Guardar en lista y luego contar)
        List<String> processed = words.stream()
                .filter(w -> w.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .toList();

        long count = processed.stream()
                .count();

        System.out.println("Opción 1 - Lista: " + processed);
        System.out.println("Opción 1 - Cantidad: " + count);
        System.out.println("-----------------------------------");

        // Opción 2: Todo en un solo bloque fluido (Usando peek para imprimir en consola)
        System.out.println("Opción 2 - Ejecución del peek:");
        long cantidad = words.stream()
                .filter(w -> w.length() > 4)
                .map(String::toUpperCase)
                .sorted()
                .peek(System.out::println)
                .count();

        System.out.println("Opción 2 - Cantidad total: " + cantidad);
    }

    public static void main(String[] args) {
        ejercicio2();
    }
}
