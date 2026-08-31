package dosw.semana_1.streams.extra;

import java.util.List;

public class Ejercicio1 {

    /**
     * Toma una lista de números y extrae únicamente aquellos que cumplen dos reglas:
     * ser pares y ser mayores a 10.
     */

    public static void ejercicio1() {
        List<Integer> numbers = List.of(3, 8, 10, 12, 15, 18, 20);

        // Opción 1: Usando múltiples .filter() en cadena
        List<Integer> result = numbers.stream()
                .filter(n -> n % 2 == 0)
                .filter(n -> n > 10)
                .toList();

        System.out.println("Opción 1 (Filtros separados): " + result);

        // Opción 2: Usando un solo .filter() con el operador lógico && (AND)
        List<Integer> resultado = numbers.stream()
                .filter(n -> n % 2 == 0 && n > 10)
                .toList();

        System.out.println("Opción 2 (Filtro compuesto): " + resultado);
    }

    public static void main(String[] args) {
        ejercicio1();
    }
}
