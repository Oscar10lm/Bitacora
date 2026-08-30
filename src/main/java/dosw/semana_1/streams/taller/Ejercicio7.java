package dosw.semana_1.streams.taller;

import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

/**PATRÓN: ORDENAMIENTO DE LISTAS
 * Uso:
 * "Dada una lista, organizar sus elementos de menor a mayor (ascendente)
 * o de mayor a menor (descendente) y guardarlos en una lista nueva".
 *
 * ESTRUCTURA CLAVE:
 * 1. .stream() -> Abre la lista.
 * 2. .sorted() -> Ordena de menor a mayor por defecto.
 *    .sorted(Comparator.reverseOrder()) -> Ordena de mayor a menor.
 * 3. .collect(Collectors.toList()) -> Empaca la lista ya ordenada.
 */

public class Ejercicio7 {

    /**
     * Toma una lista de edades desordenadas y crea dos listas nuevas:
     * una organizada ascendentemente y otra descendentemente.
     */

    public static void ejercicio7 () {
        List<Integer> edades = List.of(25, 18, 32, 21, 19, 28);
        List<Integer> ascendentes = edades.stream().sorted().collect(Collectors.toList());
        List<Integer> descendentes = edades.stream().sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());

        System.out.println("Ascendente:");
        System.out.println(ascendentes);
        System.out.println("Descendente:");
        System.out.println(descendentes);
    }

    public static void main(String[] args) {
        ejercicio7();
    }
}
